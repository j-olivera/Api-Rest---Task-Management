package com.taskManagement.domain.services;

import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.exceptions.UserNotFoundException;
import com.taskManagement.domain.mapper.UserMapper;
import com.taskManagement.domain.repository.UserRepository;
import com.taskManagement.domain.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

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
        if(user.getCreatedAt()==null){
            user.setCreatedAt(LocalDateTime.now());
        }
        return userRepository.save(user);
    }
    //PUT
    public User updateUser(Long id, UserRequest userRequest) {
        User user2 = userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("User with id " + id + " not found"));
        userMapper.updateUserentityFromRequest(userRequest, user2);
        return userRepository.save(user2);
    }
    public boolean deleteUser(Long id) {
        User userDeleted = userRepository.findById(id).
                orElseThrow(()-> new UserNotFoundException("User with id " + id + " not found"));
        userRepository.deleteById(userDeleted.getId()); //revisar aca
        return true;

    }
}
