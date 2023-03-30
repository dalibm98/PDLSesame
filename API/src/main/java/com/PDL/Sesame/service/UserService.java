package com.PDL.Sesame.service;

import com.PDL.Sesame.model.*;

import java.util.List;
import java.util.Optional;


public interface UserService {

  //  User register(User user);
    Optional<User> findByEmail(String email);
/*
  //  User login(String email, String password);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    User getCurrentUser();

    User addQuestion(Question question);

    User addReponse(Reponse reponse);

    List<User> getUsersByRole(RoleEnum roleEnum);

 */
}
