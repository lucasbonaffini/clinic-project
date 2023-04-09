package com.project.clinic.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.clinic.dto.AppointmentDTO;
import com.project.clinic.exceptions.BadRequestException;
import com.project.clinic.exceptions.ResourceNotFoundException;
import com.project.clinic.model.Address;
import com.project.clinic.model.Appointment;
import com.project.clinic.model.Dentist;
import com.project.clinic.model.Patient;
import com.project.clinic.repository.AppointmentRepository;
import com.project.clinic.repository.DentistRepository;
import com.project.clinic.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DentistService dentistService;

    @Autowired
    private PatientService patientService;

    @Autowired
    ObjectMapper mapper;

    Logger logger = Logger.getLogger(AppointmentService.class);


    @Transactional
    public void createAppointment(AppointmentDTO app) {
        try {

            if (dentistService.findById(app.getDentist_id()).isPresent() && patientService.findByDni(app.getPatient_id()).isPresent()) {

                validate(app.getPatient_id(), app.getDentist_id(), app.getDate(), app.getDescription());

                Patient patient = patientService.findByDni(app.getPatient_id()).get();
                Dentist dentist = dentistService.findById(app.getDentist_id()).get();

                Appointment appointment = new Appointment();
                appointment.setPatient(patient);
                appointment.setDentist(dentist);

                appointment.setDate(app.getDate());
                appointment.setDescription(app.getDescription());

                appointmentRepository.save(appointment);
            } else {
                throw new ResourceNotFoundException("Patient or Dentist doesn't found in our database");
            }
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Appointment> listAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        appointments = appointmentRepository.findAll();
        return appointments;
    }

    @Transactional
    public void deleteAppointment(Integer id) throws ResourceNotFoundException, BadRequestException {

        if (id == null || id < 1) {
            throw new BadRequestException("Id can't be null or 0");
        }
        Optional<Appointment> response = appointmentRepository.findById(id);

        if (response.isPresent()) {
            Appointment appointment = response.get();
            appointmentRepository.delete(appointment);
        } else {
            throw new ResourceNotFoundException("Appointment " + id + " not found");
        }
    }

    public List<Appointment> searchAppointmentByEnrollment(Integer id) {
        List<Appointment> allAppointments = new ArrayList<>();
        allAppointments = appointmentRepository.findByDentistId(id);
        return allAppointments;

    }

    public Appointment searchAppointment(Integer id) throws ResourceNotFoundException {
        Optional<Appointment> response = appointmentRepository.findById(id);
        Appointment appointment = null;
        try {
            if (id < 1) {
                throw new BadRequestException("Id can't be null or 0");
            }
        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }
        if (response.isPresent()) {
            appointment = response.get();
        } else {
            throw new ResourceNotFoundException("The appointment " + id + " doesn't exist in our database");
        }
        return appointment;
    }

    @Transactional
    public void modifyAppointment(AppointmentDTO app) {
        try {
            validate(app.getPatient_id(), app.getDentist_id(), app.getDate(), app.getDescription());

            Appointment response = appointmentRepository.findById(app.getId()).orElse(null);

            if (response != null) {
                response.setDate(app.getDate());
                response.setDescription(app.getDescription());

                appointmentRepository.save(response);
            } else {
                throw new ResourceNotFoundException("Appointment doesn't exist");
            }

        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void validate(Integer dni, Integer enrollment, Date date, String desc) {
        try {
            if (dni == null || dni < 0) {
                throw new BadRequestException("dni can't be null or fewer than 0");
            }
            if (enrollment == null || enrollment < 0) {
                throw new BadRequestException("enrollment can't be null or fewr than 0");
            }
            if (date == null || date.compareTo(new Date()) < 0) {
                throw new BadRequestException("date can't be null or before today");
            }
            if (desc == null || desc.isEmpty()) {
                throw new BadRequestException("description can't be null of empty");
            }

        } catch (BadRequestException e) {
            throw new RuntimeException(e);
        }

    }


}
