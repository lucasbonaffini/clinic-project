package com.project.clinic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.clinic.dto.PatientDTO;
import com.project.clinic.exceptions.BadRequestException;
import com.project.clinic.exceptions.ResourceNotFoundException;
import com.project.clinic.model.Address;
import com.project.clinic.model.Patient;
import com.project.clinic.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class PatientService {


    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    ObjectMapper mapper;


    @Transactional
    public void createPatient(PatientDTO pat) {
        validate(pat.getLastname(), pat.getName(), pat.getDni(), pat.getAddress());

        pat.setRegistrationDate(new Date());

        savePatient(pat);

    }


    public Collection<PatientDTO> getAll() {
        List<Patient> allPatients = patientRepository.findAll();
        Set<PatientDTO> allPatientsDTO = new HashSet<PatientDTO>();
        for (Patient patient : allPatients)
            allPatientsDTO.add(mapper.convertValue(patient, PatientDTO.class));

        return allPatientsDTO;
    }

    @Transactional
    public void deletePatient(Integer id) throws BadRequestException, ResourceNotFoundException {

        if (id == null || id < 1) {
            throw new BadRequestException("id can't be null or 0");
        }
        Optional<Patient> response = patientRepository.findById(id);

        if (response.isPresent()) {
            Patient patient = response.get();
            patientRepository.delete(patient);
        } else {
            throw new ResourceNotFoundException("patient " + id + " not found");
        }
    }

    public PatientDTO searchPatient(Integer id) throws ResourceNotFoundException {
        Optional<Patient> response = patientRepository.findById(id);
        PatientDTO patient = null;
        try {
            if (id < 1) {
                throw new BadRequestException("Id can't be null or 0");
            }
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        if (response.isPresent()) {
            patient = mapper.convertValue(response, PatientDTO.class);

        } else {
            throw new ResourceNotFoundException("The patient " + id + " doesn't exist in our database");
        }
        return patient;
    }

    @Transactional
    public void modifyPatient(PatientDTO pat) {
        validate(pat.getLastname(), pat.getName(), pat.getDni(), pat.getAddress());

        savePatient(pat);

    }

    private void savePatient(PatientDTO pat) {
        Patient newPatient = mapper.convertValue(pat, Patient.class);
        patientRepository.save(newPatient);
    }


    public Set<PatientDTO> getPatientsWithLastnameLike(String lastname) {
        Set<Patient> allPatients = patientRepository.getPatientsByLastnameLike(lastname);
        Set<PatientDTO> allPatientsDTO = new HashSet<PatientDTO>();
        for (Patient patient : allPatients)
            allPatientsDTO.add(mapper.convertValue(patient, PatientDTO.class));

        return allPatientsDTO;
    }

    private void validate(String lastname, String name, Integer dni, Address address) {
        try {
            if (lastname == null || lastname.isEmpty()) {
                throw new BadRequestException("Lastname can't be null or empty");
            }
            if (name == null || name.isEmpty()) {
                throw new BadRequestException("Name can't be null or empty");
            }
            if (dni == null) {
                throw new BadRequestException("Dni can't be null");
            }
            if (address == null) {
                throw new BadRequestException("Address can't be null");
            }
            if (address.getNumber() < 0) {
                throw new BadRequestException("Address number can't be null or fewer than 0");
            }

        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<Patient> findByDni(Integer dni) {
        return patientRepository.findByDni(dni);
    }
}
