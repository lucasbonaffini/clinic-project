package com.project.clinic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.clinic.dto.DentistDTO;
import com.project.clinic.exceptions.BadRequestException;
import com.project.clinic.exceptions.ResourceNotFoundException;
import com.project.clinic.model.Dentist;
import com.project.clinic.repository.DentistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DentistService {

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    ObjectMapper mapper;


    @Transactional
    public void createDentist(DentistDTO den) {
        validate(den.getEnrollment(), den.getLastname(), den.getName());
        saveDentist(den);
    }

    public Collection<DentistDTO> getAll() {
        List<Dentist> allDentists = dentistRepository.findAll();
        Set<DentistDTO> allDentistsDTO = new HashSet<DentistDTO>();
        for (Dentist dentist : allDentists)
            allDentistsDTO.add(mapper.convertValue(dentist, DentistDTO.class));

        return allDentistsDTO;
    }

    @Transactional
    public void deleteDentist(Integer enrollment) throws BadRequestException, ResourceNotFoundException {

        if (enrollment == null || enrollment < 1) {
            throw new BadRequestException("enrollment can't be null or 0");
        }
        Optional<Dentist> response = dentistRepository.findById(enrollment);

        if (response.isPresent()) {
            Dentist dentist = response.get();
            dentistRepository.delete(dentist);
        } else {
            throw new ResourceNotFoundException("enrollment " + enrollment + " not found");
        }
    }

    public DentistDTO searchDentist(Integer enrollment) throws ResourceNotFoundException {
        Optional<Dentist> response = dentistRepository.findById(enrollment);
        DentistDTO dentist = null;
        try {
            if (enrollment < 1) {
                throw new BadRequestException("Id can't be null or 0");
            }
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        if (response.isPresent()) {
            dentist = mapper.convertValue(response, DentistDTO.class);

        } else {
            throw new ResourceNotFoundException("The dentist " + enrollment + " doesn't exist in our database");
        }
        return dentist;
    }

    @Transactional
    public void modifyDentist(DentistDTO dentist) {
        validate(dentist.getEnrollment(), dentist.getLastname(), dentist.getName());

        saveDentist(dentist);

    }

    private void saveDentist(DentistDTO den) {
        Dentist newDentist = mapper.convertValue(den, Dentist.class);
        dentistRepository.save(newDentist);
    }


    public Set<DentistDTO> getDentistsWithLastnameLike(String lastname) {
        Set<Dentist> allDentists = dentistRepository.getDentistByLastnameLike(lastname);
        Set<DentistDTO> allDentistsDTO = new HashSet<DentistDTO>();
        for (Dentist dentist : allDentists)
            allDentistsDTO.add(mapper.convertValue(dentist, DentistDTO.class));

        return allDentistsDTO;
    }


    private void validate(Integer enrollment, String lastname, String name) {
        try {
            if (lastname == null || lastname.isEmpty()) {
                throw new BadRequestException("Lastname can't be null or empty");
            }
            if (name == null || name.isEmpty()) {
                throw new BadRequestException("Name can't be null or empty");
            }
            if (enrollment == null) {
                throw new BadRequestException("enrollment can't be null");
            }
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }

    }
    public Optional<Dentist> findById(Integer enrollment) {
        return dentistRepository.findById(enrollment);
    }

}
