    package com.PDL.Sesame.auth;

    import com.PDL.Sesame.dao.UserDao;
    import com.PDL.Sesame.model.Question;
    import com.PDL.Sesame.model.Reponse;
    import com.PDL.Sesame.model.User;
    import io.swagger.v3.oas.annotations.security.SecurityRequirement;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.annotation.AuthenticationPrincipal;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    @RestController
    @RequestMapping("/api/v1/auth")
    @SecurityRequirement(name = "bearerAuth")
    @RequiredArgsConstructor
    public class AuthenticationController {

        private final AuthenticationService service;
     UserDao repository ;
        @PostMapping("/register")
        public ResponseEntity<AuthenticationResponse> register(
                @RequestBody RegisterRequest request
        ) {
            return ResponseEntity.ok(service.register(request));
        }
        @PostMapping("/authenticate")
        public ResponseEntity<AuthenticationResponse> authenticate(
                @RequestBody AuthenticationRequest request
        ) {
            return ResponseEntity.ok(service.authenticate(request));
        }

    /*
        @PostMapping("/questions")
        public ResponseEntity<String> addQuestion(@RequestBody Question question, @AuthenticationPrincipal User user) {
            question.setAuteur(user);
            user.getQuestions().add(question);
            repository.save(user);
            return ResponseEntity.ok("Question ajoutée avec succès !");
        }
    */

        @PostMapping("/questions")
        public ResponseEntity<User> addQuestion(@RequestBody Question question) {
            User user = service.addQuestion(question);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }



        @PostMapping("/reponse")
        public ResponseEntity<String> addReponse(@RequestBody Reponse reponse) {
            User user = service.getCurrentUser();
            reponse.setAuteur(user);
            user.getReponses().add(reponse);
            repository.save(user);
            return ResponseEntity.ok("Réponse ajoutée avec succès !");
        }

    }
