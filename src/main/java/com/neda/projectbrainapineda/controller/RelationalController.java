package com.neda.projectbrainapineda.controller;

import com.neda.projectbrainapineda.form.NewIdeaForm;
import com.neda.projectbrainapineda.form.TodoForm;
import com.neda.projectbrainapineda.model.Brain;
import com.neda.projectbrainapineda.model.Idea;
import com.neda.projectbrainapineda.repository.BrainRepository;
import com.neda.projectbrainapineda.repository.IdeaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelationalController {

    private final BrainRepository brainRepository;
    private final IdeaRepository ideaRepository;

    @Autowired
    public RelationalController(BrainRepository brainRepository, IdeaRepository ideaRepository) {
        this.brainRepository = brainRepository;
        this.ideaRepository = ideaRepository;
    }

    @PostMapping("/relation/save_idea_brain")
    public Idea saveIdeaToBrain(@RequestBody NewIdeaForm newIdeaForm) {
        try {
            Brain brain = brainRepository.findBrainByUsername(newIdeaForm.getUsername()).orElseThrow();

            Idea idea = new Idea();
            idea.setTitle(newIdeaForm.getTitle());
            idea.setContext(newIdeaForm.getContext());
            idea.setContent(newIdeaForm.getContent());
            idea.setAuthor(brain);

            Idea responseIdea = ideaRepository.save(idea);

            brain.getIdeas().add(responseIdea);

            brainRepository.save(brain);

            return responseIdea;
        } catch (Exception e) {
            e.printStackTrace();
            return new Idea();
        }
    }

    @PostMapping("/relation/save_todo_brain")
    public Brain saveToDoToBrain(@RequestBody TodoForm todoForm) {
        try {
            Idea idea = ideaRepository.findIdeaById(todoForm.getIdeaId()).orElseThrow();
            Brain brain = brainRepository.findBrainByUsername(todoForm.getUsername()).orElseThrow();

            brain.getTodos().add(idea);

            Brain responseBrain = brainRepository.save(brain);

            return responseBrain;
        } catch (Exception e) {
            e.printStackTrace();
            return new Brain();
        }
    }
}