package com.PDL.Sesame.service;

import com.PDL.Sesame.model.*;

import java.util.List;

public interface UserService {

    User register(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    void validateUser(Long id, String token);
    void changePassword(Long id, String oldPassword, String newPassword);
    User addQuestion(Long userId, Question question);
    User addReponse(Long userId, Reponse reponse);
    User addNotification(Long userId, Notification notification);
    List<User> getUsersByRole(RoleEnum roleEnum);
}
