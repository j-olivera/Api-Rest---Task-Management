package com.taskManagement.domain.repository;

import com.taskManagement.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    User findByUsernameAndEmail(String username, String email);
    List<User> findByActiveTrue();
    List<User> findByActiveFalse();
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
}
