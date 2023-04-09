package com.project.clinic.repository;

import com.project.clinic.model.Appointment;
import com.project.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a JOIN a.patient p WHERE p.dni = :dni")
    List<Appointment> findByPatientDni(@Param("dni") Integer dni);


    @Query("SELECT a FROM Appointment a JOIN a.dentist d WHERE dentist.enrollment = :enrollment")
    List<Appointment> findByDentistId(@Param("enrollment") Integer enrollment);
}
