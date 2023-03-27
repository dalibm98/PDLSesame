package com.PDL.Sesame.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;


import java.util.List;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_question;

    private String sujet;

    private String contenu;

    private LocalDateTime date;

    @ManyToOne
    private User auteur;
    @JsonIgnore
    @OneToMany(mappedBy = "question")
    private List<Reponse> reponses;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_nature_question")
    private NatureQuestion nature;
    @JsonIgnore
    @ManyToOne
    private DomaineQuestion domaine;

    @Column(name = "is_answered")
    private boolean isAnswered;

}
