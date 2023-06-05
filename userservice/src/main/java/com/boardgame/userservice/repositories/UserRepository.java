package com.boardgame.userservice.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boardgame.userservice.models.User;


public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findById(UUID id);
}