package com.PDL.Sesame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "domaine_questions")
public class DomaineQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_domaine_question;

    @Column(nullable = false, unique = true)
    private String nom_domaine_question;

    @JsonIgnore
    @OneToMany(mappedBy = "domaine")
    private List<Question> questions;

}
