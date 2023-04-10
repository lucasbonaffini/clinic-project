package com.project.clinic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "dentists")
public class Dentist {

    @Id
    private Integer enrollment;

    private String lastname;

    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dentist dentist)) return false;
        return Objects.equals(getEnrollment(), dentist.getEnrollment()) && Objects.equals(getLastname(), dentist.getLastname()) && Objects.equals(getName(), dentist.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEnrollment(), getLastname(), getName());
    }
}
