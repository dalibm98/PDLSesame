package com.PDL.Sesame.dao;

import com.PDL.Sesame.model.NatureQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NatureQuestionRepository  extends JpaRepository<NatureQuestion, Long> {

}
