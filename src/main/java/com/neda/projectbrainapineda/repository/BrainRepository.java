package com.neda.projectbrainapineda.repository;

import java.util.Optional;

import com.neda.projectbrainapineda.model.Brain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BrainRepository extends JpaRepository<Brain, Long> {

    Optional<Brain> findBrainByUsername(String username);
    Optional<Brain> findBrainByEmailAndPassword(String email, String password);
}