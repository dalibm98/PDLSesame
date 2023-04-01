package com.PDL.Sesame.dao;

import com.PDL.Sesame.model.DomaineQuestion;
import com.PDL.Sesame.model.NatureQuestion;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NatureQuestionRepository  extends JpaRepository<NatureQuestion, Long> {
    //NatureQuestion findByNom_nature_question(String nom_nature_question);
       // NatureQuestion findByNom_nature_questionIgnoreCase(String nom_nature_question);
    //NatureQuestion findByNom_nature_question(String nom_nature_question);


    }




