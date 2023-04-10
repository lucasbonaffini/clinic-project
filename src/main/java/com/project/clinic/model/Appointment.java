package com.project.clinic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dentist_id", nullable = false)
    private Dentist dentist;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    private Date date;

    private String description;

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDentist(), getPatient(), getDate(), getDescription());
    }
}
