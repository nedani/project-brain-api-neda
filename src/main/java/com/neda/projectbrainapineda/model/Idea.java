package com.neda.projectbrainapineda.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "author")
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = false, nullable = true)
    private String citeId;

    @Column(unique = false, nullable = false)
    private String title;

    @Column(unique = false, nullable = false)
    private String context;

    @Column(unique = false, nullable = false)
    private String content;

    @ManyToOne
    private Brain author;
}