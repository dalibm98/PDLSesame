package com.PDL.Sesame.service;

import com.PDL.Sesame.Exception.ResourceNotFoundException;
import com.PDL.Sesame.dao.QuestionDao;
import com.PDL.Sesame.dao.ReponseDao;
import com.PDL.Sesame.model.Question;
import com.PDL.Sesame.model.Reponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private ReponseDao reponseDao;

    @Override
    public Question poserQuestion(Question question) {
        return questionDao.save(question);
    }

    @Override
    public Reponse repondreQuestion(Long questionId, Reponse reponse) {
        Question question = questionDao.findById(questionId).orElseThrow(
                () -> new ResourceNotFoundException("Question", "id", questionId)
        );
        reponse.setQuestion(question);
        return reponseDao.save(reponse);
    }

    @Override
    public List<Question> consulterQuestions() {
        return questionDao.findAllByOrderByDateDesc();
    }

    @Override
    public List<Reponse> consulterReponses(Long questionId) {
        Question question = questionDao.findById(questionId).orElseThrow(
                () -> new ResourceNotFoundException("Question", "id", questionId)
        );
        return reponseDao.findByQuestionOrderByDateCreationAsc(question);
    }

}
