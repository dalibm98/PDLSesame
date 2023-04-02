package com.PDL.Sesame.controleur;

import com.PDL.Sesame.model.DomaineQuestion;
import com.PDL.Sesame.service.DomaineQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/api/domaine-questions")
//@Api(value = "DomaineQuestionController", tags = {"Domaine Question API"})
public class DomaineQuestionController {

    @Autowired
    private DomaineQuestionService domaineQuestionService;


    @GetMapping
    //@ApiOperation(value = "Get all DomaineQuestions", notes = "Retrieves a list of all DomaineQuestion objects.")
    public List<DomaineQuestion> getAllDomaineQuestions() {
        return domaineQuestionService.getAllDomaineQuestions();
    }


    @GetMapping("/{id}")
    //@ApiOperation(value = "Get a DomaineQuestion by ID", notes = "Retrieves a DomaineQuestion object by its ID.")
    public DomaineQuestion getDomaineQuestionById(@PathVariable Long id) {
        return domaineQuestionService.getDomaineQuestionById(id);
    }


    @PostMapping
    //@ApiOperation(value = "Create a new DomaineQuestion", notes = "Creates a new DomaineQuestion object.")
    public DomaineQuestion createDomaineQuestion(@RequestBody DomaineQuestion domaineQuestion) {
        return domaineQuestionService.createDomaineQuestion(domaineQuestion);
    }


    @PutMapping("/{id}")
    //@ApiOperation(value = "Update an existing DomaineQuestion", notes = "Updates an existing DomaineQuestion object by its ID.")
    public DomaineQuestion updateDomaineQuestion(@PathVariable Long id, @RequestBody DomaineQuestion domaineQuestion) {
        return domaineQuestionService.updateDomaineQuestion(id, domaineQuestion);
    }


    @DeleteMapping("/{id}")
    //@ApiOperation(value = "Delete a DomaineQuestion by ID", notes = "Deletes a DomaineQuestion object by its ID.")
    public void deleteDomaineQuestion(@PathVariable Long id) {
        domaineQuestionService.deleteDomaineQuestion(id);
    }
}
