package com.project.clinic.repository;

import com.project.clinic.model.Address;
import com.project.clinic.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
