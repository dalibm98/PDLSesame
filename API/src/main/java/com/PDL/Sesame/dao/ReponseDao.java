package com.PDL.Sesame.dao;

import com.PDL.Sesame.model.Question;
import com.PDL.Sesame.model.Reponse;
import com.PDL.Sesame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponseDao extends JpaRepository<Reponse, Long> {

    //List<Reponse> findByQuestion(Question question);

   // List<Reponse> findByAuteur(User user);

    List<Reponse> findByQuestionOrderByDateCreationAsc(Question question);
}
