package com.neda.projectbrainapineda.form;

import java.util.Set;

import com.neda.projectbrainapineda.model.Brain;

import lombok.Data;

@Data
public class BrainResponseForm {
    private Set<Brain> data;
}