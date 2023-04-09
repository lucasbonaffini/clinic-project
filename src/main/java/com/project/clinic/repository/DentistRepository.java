package com.project.clinic.repository;

import com.project.clinic.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Integer> {
    @Query("FROM Dentist d WHERE d.lastname like %:lastname%")
    Set<Dentist> getDentistByLastnameLike(@Param("lastname") String lastname);

}
