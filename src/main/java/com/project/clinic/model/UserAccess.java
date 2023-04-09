package com.project.clinic.model;

import com.project.clinic.login.LoginUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class UserAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String mail;

    private String password;
    @Enumerated(EnumType.STRING)
    private LoginUser loginUser;


}
