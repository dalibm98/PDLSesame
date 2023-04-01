package com.PDL.Sesame.controleur;

import com.PDL.Sesame.model.*;
import com.PDL.Sesame.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
//@Api(value = "UserController", tags = { "User" })
public class UserController{
    /* {

    @Autowired
    private UserService userService;

    @Autowired
   private AuthenticationManager authenticationManager;

   @ApiOperation(value = "Register new user", response = User.class)


    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = userService.register(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestParam(required = true) String email, @RequestParam String password) {
        User user = userService.login(email, password);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }





    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getMot_de_passe();

        User user = userService.login(email, password);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }








    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
        User user = userService.login(loginRequest.getEmail(), loginRequest.getMot_de_passe());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getMot_de_passe());
        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        return ResponseEntity.ok(user);
    }



  @ApiOperation(value = "Get all users", response = List.class)













    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //@ApiOperation(value = "Get user by ID", response = User.class)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //@ApiOperation(value = "Update user", response = User.class)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    //@ApiOperation(value = "Delete user")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@ApiOperation(value = "Get current user", response = User.class)
    @GetMapping("/current")
    public ResponseEntity<User> getCurrentUser() {
        User user = userService.getCurrentUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //@ApiOperation(value = "Add question", response = User.class)
    @PostMapping("/questions")
    public ResponseEntity<User> addQuestion(@RequestBody Question question) {
        User user = userService.addQuestion(question);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //@ApiOperation(value = "Add réponse", response = User.class)
    @PostMapping("/reponses")
    public ResponseEntity<User> addReponse(@RequestBody Reponse reponse) {
        User user = userService.addReponse(reponse);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

   // @ApiOperation(value = "Get users by role", response = List.class)
    @GetMapping("/roles/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable RoleEnum role) {
        List<User> users = userService.getUsersByRole(role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


     */


}
