package com.PDL.Sesame.dao;

import com.PDL.Sesame.model.Role;
import com.PDL.Sesame.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByNomRole(RoleEnum nom_role);
}
