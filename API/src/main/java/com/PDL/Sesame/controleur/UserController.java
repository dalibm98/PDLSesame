package com.PDL.Sesame.controleur;

import com.PDL.Sesame.model.*;
import com.PDL.Sesame.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/users")
@Api(tags = "Utilisateurs")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Créer un nouvel utilisateur")
    @PostMapping("")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @ApiOperation(value = "Obtenir tous les utilisateurs")
    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "Obtenir un utilisateur par ID")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @ApiOperation(value = "Mettre à jour un utilisateur par ID")
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @ApiOperation(value = "Supprimer un utilisateur par ID")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @ApiOperation(value = "Valider un utilisateur par ID et jeton")
    @GetMapping("/{id}/validate")
    public void validateUser(@PathVariable Long id, @RequestParam String token) {
        userService.validateUser(id, token);
    }

    @ApiOperation(value = "Changer le mot de passe d'un utilisateur par ID")
    @PutMapping("/{id}/password")
    public void changePassword(@PathVariable Long id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        userService.changePassword(id, oldPassword, newPassword);
    }

    @ApiOperation(value = "Ajouter une question pour un utilisateur par ID")
    @PostMapping("/{id}/questions")
    public User addQuestion(@PathVariable Long id, @RequestBody Question question) {
        return userService.addQuestion(id, question);
    }

    @ApiOperation(value = "Ajouter une réponse pour un utilisateur par ID")
    @PostMapping("/{id}/reponses")
    public User addReponse(@PathVariable Long id, @RequestBody Reponse reponse) {
        return userService.addReponse(id, reponse);
    }

    @ApiOperation(value = "Ajouter une notification pour un utilisateur par ID")
    @PostMapping("/{id}/notifications")
    public User addNotification(@PathVariable Long id, @RequestBody Notification notification) {
        return userService.addNotification(id, notification);
    }

    @ApiOperation(value = "Obtenir tous les utilisateurs par rôle")
    @GetMapping("/roles/{role}")
    public List<User> getUsersByRole(@PathVariable RoleEnum role) {
        return userService.getUsersByRole(role);
    }
}
