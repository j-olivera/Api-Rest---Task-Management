package com.taskManagement.domain.services;

import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.exceptions.UserNotFoundException;
import com.taskManagement.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    //GET
    public User getUserById(Long id) {
        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }
    public Page<User> getAllUsers(int page, int size,String sortBy) {
        Pageable pageable = PageRequest.of(page, size,  Sort.by(sortBy));
        return userRepository.findAll(pageable); // paginación
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User getUserByUsernameAndEmail(String username, String email) {
        return userRepository.findByUsernameAndEmail(username, email);
    }
    public List<User> getUserActives() {
        return userRepository.findByActiveTrue();
    }
    public List<User> getUserInactives() {
        return userRepository.findByActiveFalse();
    }
    public List<User> getUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }
    public List<User> getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }
    //POST
    public User createUser(User user) {
        return userRepository.save(user);
    }
    //PUT
    public User updateUser(Long id, User user) {
        User user2 = userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("User with id " + id + " not found"));
        user2.setUsername(user.getUsername());
        user2.setEmail(user.getEmail());
        user2.setFirstName(user.getFirstName());
        user2.setLastName(user.getLastName());
        user2.setActive(user.getActive());

        return userRepository.save(user);
    }
    public boolean deleteUser(Long id) {
        User userDeleted = userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("User with id " + id + " not found"));
        userRepository.deleteById(userDeleted.getId()); //revisar aca
        return true;

    }
}
