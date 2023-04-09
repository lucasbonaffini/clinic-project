package com.project.clinic.repository;

import com.project.clinic.model.Dentist;
import com.project.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Query("FROM Patient p WHERE p.dni = :dni")
    Optional<Patient> findByDni(@Param("dni") Integer dni);

    @Query("FROM Patient p WHERE p.lastname like %:lastname%")
    Set<Patient> getPatientsByLastnameLike(@Param("lastname") String lastname);

}
