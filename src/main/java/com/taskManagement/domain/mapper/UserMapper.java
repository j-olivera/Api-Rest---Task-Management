package com.taskManagement.domain.mapper;

import com.taskManagement.domain.entity.User;
import com.taskManagement.domain.request.UserRequest;
import com.taskManagement.domain.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    //entity -> DTO
    public UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        return response;
    }
    //DTO -> Entity
    public User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setCreatedAt(userRequest.getCreatedAt());
        user.setActive(userRequest.getActive());
        return user;
    }
    }
