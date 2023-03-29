package com.PDL.Sesame.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_utilisateur;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String mot_de_passe;

    @ManyToOne
    private Role role;
    @JsonIgnore
    @OneToMany(mappedBy = "destinataire")
    private List<Notification> notifications;
    @JsonIgnore
    @OneToMany(mappedBy = "auteur")
    private List<Question> questions;
    @JsonIgnore
    @OneToMany(mappedBy = "auteur")

    private List<Reponse> reponses;
   // @Column(nullable = false)
  // private boolean isEnabled;
   // @Column(name = "token")
   // private String token;
}
