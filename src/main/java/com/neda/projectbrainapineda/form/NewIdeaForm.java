package com.neda.projectbrainapineda.form;

import lombok.Data;

@Data
public class NewIdeaForm {
    private String username;
    private String citeId;
    private String title;
    private String context;
    private String content;
}