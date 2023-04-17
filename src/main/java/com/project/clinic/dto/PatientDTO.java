package com.project.clinic.dto;

import com.project.clinic.model.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDTO {
    private Integer id;

    private String lastname;

    private String name;

    private Integer dni;


    private Address address;

    private Date registrationDate;
}
