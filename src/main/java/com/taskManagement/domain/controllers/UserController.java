package com.taskManagement.domain.controllers;

import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.mapper.UserMapper;
import com.taskManagement.domain.request.UserRequest;
import com.taskManagement.domain.response.UserResponse;
import com.taskManagement.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private UserMapper userMapper;

    // GET METHODS - Rutas específicas para evitar conflictos
    @GetMapping("/paginated")
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        Page<User> users = userService.getAllUsers(page, size, sortBy);
        Page<UserResponse> usersResponse = users.map(userMapper::toResponse);
        return ResponseEntity.ok(usersResponse);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        UserResponse userResponse = userMapper.toResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        UserResponse userResponse = userMapper.toResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(@PathVariable("email") String email) {
        User user = userService.getUserByEmail(email);
        UserResponse userResponse = userMapper.toResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/active")
    public ResponseEntity<List<UserResponse>> getActiveUsers() {
        List<User> users = userService.getUserActives();
        List<UserResponse> usersResponse = users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usersResponse);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<UserResponse>> getInactiveUsers() {
        List<User> users = userService.getUserInactives();
        List<UserResponse> userResponses = users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/firstname/{firstname}")
    public ResponseEntity<List<UserResponse>> getUsersByFirstName(@PathVariable("firstname") String firstName) {
        List<User> users = userService.getUserByFirstName(firstName);
        List<UserResponse> userResponses = users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<List<UserResponse>> getUsersByLastName(@PathVariable("lastname") String lastName) {
        List<User> users = userService.getUserByLastName(lastName);
        List<UserResponse> userResponses = users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userResponses);
    }

    // POST METHOD
    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest); //los datos recibidos a entidad
        User createdUser = userService.createUser(user);//creamos user con los datos recibidos
        UserResponse userResponse = userMapper.toResponse(createdUser);//lo transformamos a dto
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    // PUT METHOD
    @PutMapping("/id/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
        User updateUser = userService.updateUser(id, userRequest);
        UserResponse userResponse = userMapper.toResponse(updateUser);
        return ResponseEntity.ok(userResponse);
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