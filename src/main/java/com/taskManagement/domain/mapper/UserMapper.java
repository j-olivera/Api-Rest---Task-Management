package com.taskManagement.domain.mapper;

import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.request.UserRequest;
import com.taskManagement.domain.response.UserResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    //entity -> DTO
    public UserResponse toResponse(User user) {
        if(user == null){
            return null;
        }
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        response.setActive(user.getActive());
        return response;
    }
    //DTO -> Entity
    public User toEntity(UserRequest userRequest) {
        if(userRequest==null){
            return null;
        }
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setActive(userRequest.getActive());
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    //para actualizar un entity con datos de request
    public void updateUserentityFromRequest(UserRequest userRequest, User existingUser){
        if(userRequest==null ||  existingUser==null){
            return;
        }
        existingUser.setUsername(userRequest.getUsername());
        existingUser.setFirstName(userRequest.getFirstName());
        existingUser.setLastName(userRequest.getLastName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setActive(userRequest.getActive());
    }

    }
