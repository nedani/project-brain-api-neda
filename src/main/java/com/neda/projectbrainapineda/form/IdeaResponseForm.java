package com.neda.projectbrainapineda.form;
import java.util.Set;

import com.neda.projectbrainapineda.model.Idea;

import lombok.Data;

@Data
public class IdeaResponseForm {
    private Set<Idea> data;
}