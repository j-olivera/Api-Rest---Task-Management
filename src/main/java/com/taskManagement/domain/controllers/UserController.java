package com.taskManagement.domain.controllers;

import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    // GET METHODS - Rutas específicas para evitar conflictos
    @GetMapping("/paginated")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<User> users = userService.getAllUsers(page, size, sortBy);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/active")
    public ResponseEntity<List<User>> getActiveUsers() {
        List<User> users = userService.getUserActives();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<User>> getInactiveUsers() {
        List<User> users = userService.getUserInactives();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/firstname/{firstname}")
    public ResponseEntity<List<User>> getUsersByFirstName(@PathVariable("firstname") String firstName) {
        List<User> users = userService.getUserByFirstName(firstName);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<List<User>> getUsersByLastName(@PathVariable("lastname") String lastName) {
        List<User> users = userService.getUserByLastName(lastName);
        return ResponseEntity.ok(users);
    }

    // POST METHOD
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    // PUT METHOD
    @PutMapping("/id/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    // DELETE METHOD
    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        boolean ok = userService.deleteUser(id);
        if (ok) {
            return ResponseEntity.ok("User has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with id " + id + " not found");
        }
    }
}