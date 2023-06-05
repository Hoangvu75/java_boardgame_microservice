package com.boardgame.contractservice.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boardgame.contractservice.models.Contract;



public interface ContractRepository extends JpaRepository<Contract, UUID> {
    Optional<Contract> findById(UUID id);
}