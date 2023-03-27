package com.PDL.Sesame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notification;

    @Column(nullable = false)
    private String message;

    @Column(name = "est_lu")
    private Boolean estLu;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate = new Date();
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "destinataire_id_utilisateur")
    private User destinataire;
}