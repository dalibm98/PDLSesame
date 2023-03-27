package com.PDL.Sesame.service;

import com.PDL.Sesame.model.Question;
import com.PDL.Sesame.model.Reponse;

import java.util.List;

public interface QuestionService {
    Question poserQuestion(Question question);
    Reponse repondreQuestion(Long questionId, Reponse reponse);
    List<Question> consulterQuestions();
    List<Reponse> consulterReponses(Long questionId);
}
