package com.project.clinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Getter @Setter
@NoArgsConstructor
public class Dentist {
    @Id
    private Integer enrollment;

    private String lastname;

    private String name;



}
