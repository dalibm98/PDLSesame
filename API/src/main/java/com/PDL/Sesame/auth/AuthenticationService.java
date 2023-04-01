package com.PDL.Sesame.auth;
import com.PDL.Sesame.NewC.JwtService;
import com.PDL.Sesame.dao.QuestionDao;
import com.PDL.Sesame.dao.ReponseDao;
import com.PDL.Sesame.dao.UserDao;
import com.PDL.Sesame.model.*;
import com.PDL.Sesame.token.Token;
import com.PDL.Sesame.token.TokenRepository;
import com.PDL.Sesame.token.TokenType;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDao repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private  final UserDao userDao ;
    private final QuestionDao questionDao ;


    private  final ReponseDao reponseDao ;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.USER)
                .notifications(new ArrayList<>()) // Créer une nouvelle liste vide de notifications
                .questions(new ArrayList<>()) // Créer une nouvelle liste vide de questions
                .reponses(new ArrayList<>()) // Créer une nouvelle liste vide de réponses
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional(readOnly = true)
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            user = userDao.findById(user.getId().longValue()).orElse(null); // récupère l'utilisateur depuis la base de données pour éviter l'exception LazyInitializationException
            Hibernate.initialize(user.getQuestions()); // force l'initialisation de la collection de questions
            Hibernate.initialize(user.getTokens()); // force l'initialisation de la collection de tokens
            return user;
        }
        return null;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public User addQuestion(Question question) {
        User user = getCurrentUser();
        if (user != null) {
            question.setAuteur(user);
            questionDao.save(question); // sauvegarde la question dans la base de données
            user.getQuestions().add(question);
            userDao.save(user); // sauvegarde l'utilisateur dans la base de données avec la question ajoutée
            return user;
        }
        return null;
    }


    @PreAuthorize("hasRole('ROLE_USER')")
    @Transactional
    public User addReponse(@RequestParam Long questionId, @RequestBody Reponse reponse) {
        User user = getCurrentUser();
        if (user != null) {
            Question question = questionDao.findById(questionId).orElse(null);
            if (question == null) {
                return null; // retourne null si la question n'existe pas
            }
            reponse.setAuteur(user);
            reponse.setQuestion(question);
            reponseDao.save(reponse); // sauvegarde la réponse dans la base de données
            user.getReponses().add(reponse);
            userDao.save(user); // sauvegarde l'utilisateur dans la base de données avec la réponse ajoutée
            return user;
        }
        return null;
    }

    public Question getQuestionById(long id) {
        Optional<Question> optionalQuestion = questionDao.findById(id);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        }
        return null;
    }

    public List<QuestionWithReponses> getAllQuestionsWithReponses() {
        List<QuestionWithReponses> questionsWithReponses = new ArrayList<>();
        List<Question> questions = questionDao.findAll();
        for (Question question : questions) {
            List<Reponse> reponses = reponseDao.findByQuestionOrderByDateCreationAsc(question);
            questionsWithReponses.add(new QuestionWithReponses(question, reponses));
        }
        return questionsWithReponses;
    }

}
