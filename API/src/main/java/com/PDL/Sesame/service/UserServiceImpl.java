package com.PDL.Sesame.service;

import com.PDL.Sesame.Exception.ResourceNotFoundException;
import com.PDL.Sesame.config.InvalidPasswordException;
import com.PDL.Sesame.config.InvalidTokenException;
import com.PDL.Sesame.dao.RoleDao;
import com.PDL.Sesame.dao.UserDao;
import com.PDL.Sesame.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EmailService emailService;

    @Override
    public User register(User user) {



        user.setEnabled(false);

        user.setRole(roleDao.findByNomRole(RoleEnum.ETUDIANT));

        user.setToken(UUID.randomUUID().toString());

        User savedUser = userDao.save(user);

        sendValidationEmail(savedUser);
        return savedUser;
    }

    private void sendValidationEmail(User user) {

        String subject = "Activation de votre compte";
        String body = "Bonjour " + user.getPrenom() + " " + user.getNom() + ",\n\n"
                + "Merci de vous être inscrit à notre plateforme de partage de connaissances. Veuillez cliquer sur le lien suivant pour activer votre compte :\n\n"
                + "http://localhost:8080/api/users/" + user.getId_utilisateur() + "/validate?token=" + user.getToken() + "\n\n"
                + "Cordialement,\n"
                + "L'équipe de notre plateforme de partage de connaissances";
        String[] recipients = {user.getEmail()};
        // Send email using email service
        emailService.sendEmail(subject, body, recipients);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        existingUser.setEmail(user.getEmail());
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setRole(user.getRole());
        return userDao.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userDao.delete(user);
    }

    @Override
    public void validateUser(Long id, String token) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        if (user.getToken() != null && user.getToken().equals(token)) {
            user.setEnabled(true);
            user.setToken(null);
            userDao.save(user);
        } else {
            throw new InvalidTokenException("Le jeton d'activation est invalide ou a expiré.");
        }
    }


    @Override
    public void changePassword(Long id, String oldPassword, String newPassword) {
        User user = userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        if (!oldPassword.equals(user.getMot_de_passe())) {
            throw new InvalidPasswordException("L'ancien mot de passe est incorrect.");
        }

        user.setMot_de_passe(newPassword);
        userDao.save(user);
    }


    @Override
    public User addQuestion(Long userId, Question question) {
        User user = userDao.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        question.setAuteur(user);
        user.getQuestions().add(question);
        return userDao.save(user);
    }

    @Override
    public User addReponse(Long userId, Reponse reponse) {
        User user = userDao.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        reponse.setAuteur(user);
        user.getReponses().add(reponse);
        return userDao.save(user);
    }

    @Override
    public User addNotification(Long userId, Notification notification) {
        User user = userDao.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        notification.setDestinataire(user);
        user.getNotifications().add(notification);
        return userDao.save(user);
    }

    @Override
    public List<User> getUsersByRole(RoleEnum roleEnum) {
        Role role = roleDao.findByNomRole(roleEnum);
        return userDao.findByRole(role);
    }


}
