package com.PDL.Sesame.dao;

import com.PDL.Sesame.model.Role;
import com.PDL.Sesame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

 //   User findByEmail(String email);

    List<User> findByRole(Role role);

 //   List<User> findByIsEnabledFalse();
}