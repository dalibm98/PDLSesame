package com.PDL.Sesame.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reponse;

    private String contenu;

    private LocalDateTime dateCreation;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private User auteur;
    @JsonIgnore
    @ManyToOne
    private Question question;

    @ManyToMany
    private List<User> vote_utilisateur;

}
