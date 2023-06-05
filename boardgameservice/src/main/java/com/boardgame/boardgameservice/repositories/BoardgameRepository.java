package com.boardgame.boardgameservice.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boardgame.boardgameservice.models.Boardgame;

public interface BoardgameRepository extends JpaRepository<Boardgame, UUID> {
    Optional<Boardgame> findById(UUID id);
}
