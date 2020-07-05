package com.neda.projectbrainapineda.controller;

import com.neda.projectbrainapineda.form.BrainForm;
import com.neda.projectbrainapineda.model.Brain;
import com.neda.projectbrainapineda.repository.BrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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

    @PostMapping("/brains")
    public Brain save(@RequestBody BrainForm brainForm) {
        Brain brain = new Brain();
        brain.setEmail(brainForm.getEmail());
        brain.setPassword(brainForm.getPassword());
        brain.setUsername(brainForm.getUsername());
        brain.setFirstName(brainForm.getFirstName());
        brain.setLastname(brainForm.getLastname());
        return brainRepository.save(brain);
    }
}