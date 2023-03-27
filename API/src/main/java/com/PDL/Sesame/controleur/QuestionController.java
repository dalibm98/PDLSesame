package com.PDL.Sesame.controleur;

import com.PDL.Sesame.model.Question;
import com.PDL.Sesame.model.Reponse;
import com.PDL.Sesame.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Operation(summary = "Poser une question")
    @PostMapping("")
    public Question poserQuestion(@RequestBody Question question) {
        return questionService.poserQuestion(question);
    }

    @Operation(summary = "Répondre à une question")
    @PostMapping("/{questionId}/reponses")
    public Reponse repondreQuestion(@PathVariable Long questionId, @RequestBody Reponse reponse) {
        return questionService.repondreQuestion(questionId, reponse);
    }

    @Operation(summary = "Consulter toutes les questions")
    @GetMapping("")
    public List<Question> consulterQuestions() {
        return questionService.consulterQuestions();
    }

    @Operation(summary = "Consulter les réponses d'une question")
    @GetMapping("/{questionId}/reponses")
    public List<Reponse> consulterReponses(@PathVariable Long questionId) {
        return questionService.consulterReponses(questionId);
    }

}
