package com.neda.projectbrainapineda.form;

import javax.persistence.Column;

import lombok.Data;

@Data
public class FollowForm {
    
    @Column(name="username")
    private String username;

    @Column(name="username")
    private String usernameToBeFollowed;
}