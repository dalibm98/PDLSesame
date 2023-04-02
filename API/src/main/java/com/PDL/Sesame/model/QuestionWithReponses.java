package com.PDL.Sesame.model;

import java.util.List;

public class QuestionWithReponses {
    private final Question question;
    private final List<Reponse> reponses;

    public QuestionWithReponses(Question question, List<Reponse> reponses) {
        this.question = question;
        this.reponses = reponses;
    }

    public Question getQuestion() {
        return question;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }
}
