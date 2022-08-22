package com.esof.escolaesof.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esof.escolaesof.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    User findUserByUsername(String username);

    User findUserByEmail(String newEmail);

    Optional<User> findByEmail(String username);
}