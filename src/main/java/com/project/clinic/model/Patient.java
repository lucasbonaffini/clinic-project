package com.project.clinic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String lastname;

    private String name;

    private Integer dni;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    private Date registrationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient patient)) return false;
        return Objects.equals(getId(), patient.getId()) && Objects.equals(getLastname(), patient.getLastname()) && Objects.equals(getName(), patient.getName()) && Objects.equals(getDni(), patient.getDni()) && Objects.equals(getAddress(), patient.getAddress()) && Objects.equals(getRegistrationDate(), patient.getRegistrationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLastname(), getName(), getDni(), getAddress(), getRegistrationDate());
    }
}
