package com.PDL.Sesame.dao;

import com.PDL.Sesame.model.Role;
import com.PDL.Sesame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

    List<User> findByRole(Role role);


 //   List<User> findByIsEnabledFalse();
}