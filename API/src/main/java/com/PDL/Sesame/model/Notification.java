package com.PDL.Sesame.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


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
    private Date creationDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "destinataire_id_utilisateur")
    private User destinataire;

    public Notification(String message, Boolean estLu, Date creationDate, User destinataire) {
        this.message = message;
        this.estLu = estLu;
        this.creationDate = creationDate;
        this.destinataire = destinataire;
    }
}
