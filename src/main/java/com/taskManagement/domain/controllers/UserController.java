package com.taskManagement.domain.controllers;

import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //GET
    @GetMapping("/paginated")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<User> users = userService.getAllUsers(page,size,sortBy);
        return ResponseEntity.ok(users);
    }
    @GetMapping(path="/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
    @GetMapping(path="/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return  userService.getUserByUsername(username);
    }
    @GetMapping(path = "/{email}")
    public User getUserByEmail(@PathVariable("email") String email) {
        return  userService.getUserByEmail(email);
    }
    @GetMapping(path = "/active")
    public List<User> getActiveUsers() {
        return userService.getUserActives();
    }
    @GetMapping(path = "/inactive")
    public List<User> getInactiveUsers() {
        return userService.getUserInactives();
    }
    @GetMapping(path = "/{firstname}")
    public List<User> getUsersByFirstName(@PathVariable("firstname") String firstName) {
        return userService.getUserByFirstName(firstName);
    }
    @GetMapping(path = "/{lastname}")
    public List<User> getUsersByLastName(@PathVariable("lastname") String lastName) {
        return userService.getUserByLastName(lastName);
    }
    //POST
    @PostMapping()
    public User newUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    //PUT
    @PutMapping(path = "/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    //DELETE
    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        boolean ok = userService.deleteUser(id);
        if (ok) {
            return "User has been deleted";
        }else {
            return "User with id " + id + " not found";
        }
    }

}
