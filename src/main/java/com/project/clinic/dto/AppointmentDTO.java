package com.project.clinic.dto;

import com.project.clinic.model.Dentist;
import com.project.clinic.model.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
public class AppointmentDTO {
    private Integer id;
    private Integer patient_id;
    private Integer dentist_id;
    private Date date;
    private String description;
}
