package com.PDL.Sesame.service;

import com.PDL.Sesame.dao.DomaineQuestionRepository;
import com.PDL.Sesame.model.DomaineQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DomaineQuestionService {

    @Autowired
    private DomaineQuestionRepository domaineQuestionRepository;

    public List<DomaineQuestion> getAllDomaineQuestions() {
        return domaineQuestionRepository.findAll();
    }

    public DomaineQuestion getDomaineQuestionById(Long id) {
        Optional<DomaineQuestion> optionalDomaineQuestion = domaineQuestionRepository.findById(id);
        return optionalDomaineQuestion.orElse(null);
    }

    public DomaineQuestion createDomaineQuestion(DomaineQuestion domaineQuestion) {
        return domaineQuestionRepository.save(domaineQuestion);
    }

    public DomaineQuestion updateDomaineQuestion(Long id, DomaineQuestion domaineQuestion) {
        Optional<DomaineQuestion> optionalDomaineQuestion = domaineQuestionRepository.findById(id);
        if (optionalDomaineQuestion.isPresent()) {
            DomaineQuestion existingDomaineQuestion = optionalDomaineQuestion.get();
            existingDomaineQuestion.setNom_domaine_question(domaineQuestion.getNom_domaine_question());
            return domaineQuestionRepository.save(existingDomaineQuestion);
        } else {
            return null;
        }
    }

    public void deleteDomaineQuestion(Long id) {
        Optional<DomaineQuestion> optionalDomaineQuestion = domaineQuestionRepository.findById(id);
        if (optionalDomaineQuestion.isPresent()) {
            DomaineQuestion existingDomaineQuestion = optionalDomaineQuestion.get();
            domaineQuestionRepository.delete(existingDomaineQuestion);
        }
    }
}
