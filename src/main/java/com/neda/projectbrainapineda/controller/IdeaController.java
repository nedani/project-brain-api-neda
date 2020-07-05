package com.neda.projectbrainapineda.controller;

import com.neda.projectbrainapineda.repository.IdeaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.neda.projectbrainapineda.form.IdeaForm;
import com.neda.projectbrainapineda.model.Idea;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class IdeaController {
    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaController(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    @GetMapping("/ideas")
    public Collection<Idea> findAll() {
        return ideaRepository.findAll();
    }

    @GetMapping("/idea")
    public Idea findOne(@RequestParam Long id) {
        return ideaRepository.findIdeaById(id).orElseThrow();
    }

    @PostMapping("/ideas")
    public Idea save(@RequestBody IdeaForm ideaForm) {
        Idea idea = new Idea();
        idea.setTitle(ideaForm.getTitle());
        idea.setContext(ideaForm.getContent());
        idea.setContent(ideaForm.getContent());
        return ideaRepository.save(idea);
    }
}