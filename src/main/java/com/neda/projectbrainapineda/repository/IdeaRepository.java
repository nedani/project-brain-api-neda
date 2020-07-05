package com.neda.projectbrainapineda.repository;

import java.util.Optional;

import com.neda.projectbrainapineda.model.Idea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
    Optional<Idea> findIdeaById(Long id); 
}