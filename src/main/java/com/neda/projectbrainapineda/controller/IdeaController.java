package com.neda.projectbrainapineda.controller;

import com.neda.projectbrainapineda.repository.IdeaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.neda.projectbrainapineda.form.IdeaForm;
import com.neda.projectbrainapineda.form.IdeaResponseForm;
import com.neda.projectbrainapineda.model.Idea;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
public class IdeaController {
    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaController(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    @GetMapping("/ideas")
    public IdeaResponseForm findAll() {
        IdeaResponseForm responseForm = new IdeaResponseForm();
        try {
            responseForm.setData(new HashSet<Idea>(ideaRepository.findAll()));
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setData(new HashSet<Idea>());
        }
        return responseForm;
    }

    @GetMapping("/idea")
    public Idea findOne(@RequestParam Long id) {
        return ideaRepository.findIdeaById(id).orElseThrow();
    }

    @PostMapping("/save_ideas")
    public Idea save(@RequestBody IdeaForm ideaForm) {
        Idea idea = new Idea();
        idea.setCiteId(ideaForm.getCiteId());;
        idea.setTitle(ideaForm.getTitle());
        idea.setContext(ideaForm.getContent());
        idea.setContent(ideaForm.getContent());
        return ideaRepository.save(idea);
    }

    @GetMapping(value = "/{title}/ideas")
    public IdeaResponseForm getIdeasByTitle(@PathVariable String title) {
        IdeaResponseForm responseForm = new IdeaResponseForm();
        try {
            Set<Idea> ideasByTitle = ideaRepository.findIdeaByTitleContainingIgnoreCase(title);

            if(ideasByTitle == null) {
                responseForm.setData(new HashSet<>());
            } else {
                responseForm.setData(ideasByTitle);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            responseForm.setData(new HashSet<Idea>());
        }
        return responseForm;
    }

    @DeleteMapping("/idea/remove_idea")
    public String removeOne(@RequestParam Long id) {
        Idea idea = ideaRepository.findById(id).orElseThrow();
        idea.getAuthor().getIdeas().remove(idea);
        idea.setAuthor(null);

        ideaRepository.save(idea);

        ideaRepository.deleteById(id);
        
        return "deleted";
    }
}