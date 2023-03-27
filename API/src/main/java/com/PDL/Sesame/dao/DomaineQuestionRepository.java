package com.PDL.Sesame.dao;

import com.PDL.Sesame.model.DomaineQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomaineQuestionRepository extends JpaRepository<DomaineQuestion, Long> {

}
