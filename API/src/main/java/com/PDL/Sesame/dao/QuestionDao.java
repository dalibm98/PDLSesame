package com.PDL.Sesame.dao;

import com.PDL.Sesame.model.Question;
import com.PDL.Sesame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Long> {

   // List<Question> findByAuteur(User auteur);

//    List<Question> findByAuteurAndIsAnsweredTrue(User auteur);

   // List<Question> findByIsAnsweredTrue();

    List<Question> findAllByOrderByDateDesc();

}
