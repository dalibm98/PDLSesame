    package com.PDL.Sesame.auth;
    import ch.qos.logback.core.model.Model;
    import com.PDL.Sesame.Exception.ResourceNotFoundException;
    import com.PDL.Sesame.dao.*;
    import com.PDL.Sesame.model.*;
    import io.swagger.v3.oas.annotations.security.SecurityRequirement;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import java.time.LocalDateTime;
    import java.util.Date;
    import java.util.List;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api/v1/auth")
    @SecurityRequirement(name = "bearerAuth")
    //@RequiredArgsConstructor
    public class AuthenticationController {

        private final AuthenticationService service;

        private final QuestionDao questionDao;

        private final UserDao repository;

        private final NatureQuestionRepository natureDao ;
        private final DomaineQuestionRepository domaineDao ;
        private final NotificationDao notificationDao;



        @Autowired
        public AuthenticationController(AuthenticationService service, QuestionDao questionDao, UserDao repository, NotificationDao notificationDao ,NatureQuestionRepository natureDao ,DomaineQuestionRepository domaineDao) {
            this.service = service;
            this.questionDao = questionDao;
            this.repository = repository;
            this.notificationDao = notificationDao;
            this.natureDao = natureDao ;
            this.domaineDao =domaineDao ;
        }
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
        public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
            User user = service.getCurrentUser();
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            service.addQuestion(question);
            return ResponseEntity.ok(question);
        }

 */


        @PostMapping("/questions")
        public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
            User user = service.getCurrentUser();
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            NatureQuestion natureQuestion = null;
            if (question.getNature() != null && question.getNature().getId_nature_question() != null) {
                Optional<NatureQuestion> optionalNatureQuestion = natureDao.findById(question.getNature().getId_nature_question());
                if (optionalNatureQuestion.isPresent()) {
                    natureQuestion = optionalNatureQuestion.get();
                    System.out.println("NatureQuestion récupérée : " + natureQuestion.toString());
                }
            }

            DomaineQuestion domaineQuestion = null;
            if (question.getDomaine() != null && question.getDomaine().getId_domaine_question() != null) {
                Optional<DomaineQuestion> optionalDomaineQuestion = domaineDao.findById(question.getDomaine().getId_domaine_question());
                if (optionalDomaineQuestion.isPresent()) {
                    domaineQuestion = optionalDomaineQuestion.get();
                    System.out.println("DomaineQuestion récupérée : " + domaineQuestion.toString());
                }
            }
            question.setNature(natureQuestion);
            question.setDomaine(domaineQuestion);
            question.setAuteur(user);
            question.setAnswered(false);
            question.setDate(LocalDateTime.now());
            service.addQuestion(question);
            return ResponseEntity.ok(question);
        }



        @GetMapping("/questions/{id}")
        public ResponseEntity<Question> getQuestionById(@PathVariable long id) {
            Question question = service.getQuestionById(id);
            if (question == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(question);
        }


/*
        @PostMapping("/questions")
        @Operation(summary = "Add a new question")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Question created"),
                @ApiResponse(responseCode = "401", description = "Unauthorized")})
        public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
            User user = service.getCurrentUser();
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            if (question.getNature() != null && question.getNature().getId_nature_question() != null) {
                Optional<NatureQuestion> optionalNature = natureDao.findById(question.getNature().getId_nature_question());
                if (optionalNature.isPresent()) {
                    question.setNature(optionalNature.get());
                }
            }

            if (question.getDomaine() != null && question.getDomaine().getId_domaine_question() != null) {
                Optional<DomaineQuestion> optionalDomaine = domaineDao.findById(question.getDomaine().getId_domaine_question());
                if (optionalDomaine.isPresent()) {
                    question.setDomaine(optionalDomaine.get());
                }
            }

            question.setAuteur(user);
            question.setDate(LocalDateTime.now());

            service.addQuestion(question);

            return ResponseEntity.ok(question);
        }

 */





/*
        @PostMapping("/questions/{questionId}/reponses")
        public ResponseEntity<User> addReponse(@PathVariable Long questionId, @RequestBody Reponse reponse) {
            User user = service.addReponse(questionId, reponse);
            if (user == null) {
                return ResponseEntity.notFound().build(); // retourne une réponse HTTP 404 si l'utilisateur n'est pas trouvé
            }
            return ResponseEntity.ok(user);
        }




 */

        @PostMapping("/questions/{questionId}/reponses")
        public ResponseEntity<User> addReponse(@PathVariable Long questionId, @RequestBody Reponse reponse) {
            User user = service.addReponse(questionId, reponse);
            if (user == null) {
                return ResponseEntity.notFound().build(); // retourne une réponse HTTP 404 si l'utilisateur n'est pas trouvé
            }

            // Envoyer une notification à l'utilisateur qui a posé la question
            Question question = questionDao.findById(questionId).orElse(null);
            if (question != null && question.getAuteur() != null && !question.getAuteur().equals(user)) {

                //String message = String.format("La question '%s' a une nouvelle réponse.", question.getSujet());

                String message = String.format("La question '%s' a une nouvelle réponse ajoutée par '%s'.", question.getSujet(), user.getUsername());
                Notification notification = new Notification(message, false, new Date(), question.getAuteur());
                notificationDao.save(notification);
            }

            return ResponseEntity.ok(user);
        }


        @GetMapping("/current-user")
        public ResponseEntity<User> getCurrentUser() {
            User user = service.getCurrentUser();
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user);
        }


/*
        @PutMapping("/notifications/{notificationId}")
        public ResponseEntity<Notification> markNotificationAsRead(@PathVariable Long notificationId) {
            User user = service.getCurrentUser();
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            Notification notification = user.getNotifications().stream().filter(n -> n.getId_notification().equals(notificationId)).findFirst().orElse(null);
            if (notification == null) {
                return ResponseEntity.notFound().build();
            }

            notification.setEstLu(true);
            return ResponseEntity.ok(notification);
        }

 */


        @PutMapping("/notifications/{notificationId}")
        public ResponseEntity<Notification> markNotificationAsRead(@PathVariable Long notificationId) {
            User user = service.getCurrentUser();
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            Notification notification = user.getNotifications().stream().filter(n -> n.getId_notification().equals(notificationId)).findFirst().orElse(null);
            if (notification == null) {
                return ResponseEntity.notFound().build();
            }

            notification.setEstLu(true);
            notificationDao.save(notification); // save the update to the database

            return ResponseEntity.ok(notification);
        }


        @GetMapping("/notifications")
        public ResponseEntity<List<Notification>> getNotifications() {
            User user = service.getCurrentUser();
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(user.getNotifications());
        }

        @GetMapping("/questions-with-reponses")
        public ResponseEntity<List<QuestionWithReponses>> getAllQuestionsWithReponses() {
            return ResponseEntity.ok(service.getAllQuestionsWithReponses());
        }

    }
