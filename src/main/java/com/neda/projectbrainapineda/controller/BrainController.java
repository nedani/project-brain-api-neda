package com.neda.projectbrainapineda.controller;

import com.neda.projectbrainapineda.form.BrainForm;
import com.neda.projectbrainapineda.form.FollowForm;
import com.neda.projectbrainapineda.form.LoginForm;
import com.neda.projectbrainapineda.form.UpdateForm;
import com.neda.projectbrainapineda.model.Brain;
import com.neda.projectbrainapineda.model.Idea;
import com.neda.projectbrainapineda.repository.BrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class BrainController {
    private final BrainRepository brainRepository;

    @Autowired
    public BrainController(BrainRepository brainRepository) {
        this.brainRepository = brainRepository;
    }

    @GetMapping("/brains")
    public Collection<Brain> findAll() {
        return brainRepository.findAll();
    }

    @GetMapping("/brain")
    public Brain findOneByUsername(@RequestParam String username) {
        return brainRepository.findBrainByUsername(username).orElseThrow();
    }

    // @GetMapping("/brain")
    // public Brain findOneByEmail(@RequestParam String email) {
    //     return brainRepository.findBrainByEmail(email).orElseThrow();
    // }

    @PostMapping("/brain/signup")
    public Brain save(@RequestBody BrainForm brainForm) {
        Brain brain = new Brain();
        brain.setEmail(brainForm.getEmail());
        brain.setPassword(brainForm.getPassword());
        brain.setUsername(brainForm.getUsername());
        brain.setFirstname(brainForm.getFirstname());
        brain.setLastname(brainForm.getLastname());
        Brain responseBrain = brainRepository.save(brain);
        Set<Brain> arrBrains = Collections.<Brain> emptySet();
        responseBrain.setFollowers(arrBrains);
        return responseBrain;
    }

    @PostMapping("/brain/signin")
    public Brain save(@RequestBody LoginForm brainLoginForm) {
        Brain brain = brainRepository.findBrainByEmailAndPassword(brainLoginForm.getEmail(), brainLoginForm.getPassword()).orElseThrow();
        return brain;
    }

    @GetMapping("/brain/{username}/all_ideas")
    public Set<Idea> getIdeasForBrain(@PathVariable String username) {
        try {
            return brainRepository.findBrainByUsername(username).orElseThrow().getIdeas();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<Idea>();
        }
    }

    @GetMapping("/brain/{username}/all_todos")
    public Set<Idea> getTodosForBrain(@PathVariable String username) {
        try {
            return brainRepository.findBrainByUsername(username).orElseThrow().getTodos();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<Idea>();
        }
    }

    @GetMapping("/brain/{username}/all_followers")
    public Set<Brain> getFollowersForBrain(@PathVariable String username) {
        try {
            return brainRepository.findBrainByUsername(username).orElseThrow().getFollowers();
        } catch (Exception e) {
            e.printStackTrace();
            return new HashSet<Brain>();
        }
    }

    @PostMapping("/brain/update_brain")
    public Brain save(@RequestBody UpdateForm brainUpdateProfileForm) {
        Brain brain = brainRepository.findBrainByUsername(brainUpdateProfileForm.getUsername()).orElseThrow();
        brain.setFirstname(brainUpdateProfileForm.getFirstname());
        brain.setLastname(brainUpdateProfileForm.getLastname());
        Brain responseBrain = brainRepository.save(brain);
        return responseBrain;
    }

    @PostMapping("/brain/follow_brain")
    public Brain follow(@RequestBody FollowForm brainFollowForm) {
        Brain brainToFollow = brainRepository.findBrainByUsername(brainFollowForm.getUsername()).orElseThrow();
        Brain brainToFollowed = brainRepository.findBrainByUsername(brainFollowForm.getUsernameToBeFollowed()).orElseThrow();
        brainToFollow.getFollowers().add(brainToFollowed);
        Brain responseBrain = brainRepository.save(brainToFollow);
        return responseBrain;
    }
}