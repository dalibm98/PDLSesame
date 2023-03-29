package com.PDL.Sesame.service;

import com.PDL.Sesame.Exception.ResourceNotFoundException;

import com.PDL.Sesame.dao.RoleDao;
import com.PDL.Sesame.dao.UserDao;
import com.PDL.Sesame.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.List;
import java.util.Optional;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User register(User user) {
        return userDao.save(user);
    }

    @Override
    public User login(String email, String password) {
        User user = userDao.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        if (user.getMot_de_passe().equals(password)) {
            return user;
        }
        return null;
    }


    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userDao.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }


    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        existingUser.setEmail(user.getEmail());
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setRole(user.getRole());
        return userDao.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userDao.delete(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof OAuth2AuthenticationToken) {
            OAuth2User principal = (OAuth2User) authentication.getPrincipal();
            String email = principal.getAttribute("email");

            return userDao.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        }
        return null;
    }


    @Override
    public User addQuestion(Question question) {
        User user = getCurrentUser();
        question.setAuteur(user);
        user.getQuestions().add(question);
        return userDao.save(user);
    }

    @Override
    public User addReponse(Reponse reponse) {
        User user = getCurrentUser();
        reponse.setAuteur(user);
        user.getReponses().add(reponse);
        return userDao.save(user);
    }

    @Override
    public List<User> getUsersByRole(RoleEnum roleEnum) {
        Role role = roleDao.findByNomRole(roleEnum);
        return userDao.findByRole(role);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
