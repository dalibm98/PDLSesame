package com.PDL.Sesame.model;

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
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_role;

    @Column(name = "nom_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum nomRole;
    @JsonIgnore
    @OneToMany(mappedBy = "role")

    private List<User> utilisateurs;
}
