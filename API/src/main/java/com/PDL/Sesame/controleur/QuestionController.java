package com.PDL.Sesame.controleur;

import com.PDL.Sesame.model.Question;
import com.PDL.Sesame.model.Reponse;
import com.PDL.Sesame.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
//@Api(value = "QuestionController", tags = { "Question API" })
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    //@ApiOperation(value = "Poser une question", response = Question.class)


    @PostMapping("")
    public Question poserQuestion(@RequestBody Question question) {
        return questionService.poserQuestion(question);
    }

   // @ApiOperation(value = "Répondre à une question", response = Reponse.class)

    @PostMapping("/{questionId}/reponses")
    public Reponse repondreQuestion(@PathVariable Long questionId, @RequestBody Reponse reponse) {
        return questionService.repondreQuestion(questionId, reponse);
    }

    //@ApiOperation(value = "Consulter toutes les questions", response = Question.class, responseContainer = "List")
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("")
    public List<Question> consulterQuestions() {
        return questionService.consulterQuestions();
    }

   // @ApiOperation(value = "Consulter les réponses d'une question", response = Reponse.class, responseContainer = "List")

    @GetMapping("/{questionId}/reponses")
    public List<Reponse> consulterReponses(@PathVariable Long questionId) {
        return questionService.consulterReponses(questionId);
    }

}

