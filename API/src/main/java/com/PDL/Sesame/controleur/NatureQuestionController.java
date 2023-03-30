package com.PDL.Sesame.controleur;

import com.PDL.Sesame.model.NatureQuestion;
import com.PDL.Sesame.service.NatureQuestionService;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nature-questions")
public class NatureQuestionController {

    @Autowired
    private NatureQuestionService natureQuestionService;


  //  @ApiOperation(value = "Récupérer toutes les questions de nature")
    @GetMapping
    public List<NatureQuestion> getAllNatureQuestions() {
        return natureQuestionService.getAllNatureQuestions();
    }

   // @ApiOperation(value = "Récupérer une question de nature par son id")
    @GetMapping("/{id}")
    public ResponseEntity<NatureQuestion> getNatureQuestionById(@PathVariable Long id) {
        Optional<NatureQuestion> natureQuestion = natureQuestionService.getNatureQuestionById(id);
        if (natureQuestion.isPresent()) {
            return ResponseEntity.ok(natureQuestion.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   // @ApiOperation(value = "Créer une question de nature")
    @PostMapping
    public NatureQuestion createNatureQuestion(@RequestBody NatureQuestion natureQuestion) {
        return natureQuestionService.saveNatureQuestion(natureQuestion);
    }

  //  @ApiOperation(value = "Mettre à jour une question de nature par son id")
    @PutMapping("/{id}")
    public ResponseEntity<NatureQuestion> updateNatureQuestion(@PathVariable Long id, @RequestBody NatureQuestion natureQuestion) {
        Optional<NatureQuestion> existingNatureQuestion = natureQuestionService.getNatureQuestionById(id);
        if (existingNatureQuestion.isPresent()) {
            natureQuestion.setId_nature_question(id);
            return ResponseEntity.ok(natureQuestionService.saveNatureQuestion(natureQuestion));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


   // @ApiOperation(value = "Supprimer une question de nature par son id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNatureQuestionById(@PathVariable Long id) {
        Optional<NatureQuestion> existingNatureQuestion = natureQuestionService.getNatureQuestionById(id);
        if (existingNatureQuestion.isPresent()) {
            natureQuestionService.deleteNatureQuestionById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
