package com.neda.projectbrainapineda.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Brain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = false, nullable = true)
    private String firstName;

    @Column(unique = false, nullable = true)
    private String lastname;

    @Column(unique = false, nullable = false)
    private String password;

    @ManyToMany
    private Set<Brain> followers;

    @OneToMany
    @JsonIgnore
    private Set<Idea> ideas;

}