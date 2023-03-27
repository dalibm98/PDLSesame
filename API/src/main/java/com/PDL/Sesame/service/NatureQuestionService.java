package com.PDL.Sesame.service;

import com.PDL.Sesame.dao.NatureQuestionRepository;
import com.PDL.Sesame.model.NatureQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NatureQuestionService {

    @Autowired
    private NatureQuestionRepository natureQuestionRepository;

    public List<NatureQuestion> getAllNatureQuestions() {
        return natureQuestionRepository.findAll();
    }

    public Optional<NatureQuestion> getNatureQuestionById(Long id) {
        return natureQuestionRepository.findById(id);
    }

    public NatureQuestion saveNatureQuestion(NatureQuestion natureQuestion) {
        return natureQuestionRepository.save(natureQuestion);
    }

    public void deleteNatureQuestionById(Long id) {
        natureQuestionRepository.deleteById(id);
    }
}
