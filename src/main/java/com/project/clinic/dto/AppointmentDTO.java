package com.project.clinic.dto;

import com.project.clinic.model.Dentist;
import com.project.clinic.model.Patient;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentDTO {
    private Integer id;
    private Integer patient_id;
    private Integer dentist_id;
    private Date date;
    private String description;
}
