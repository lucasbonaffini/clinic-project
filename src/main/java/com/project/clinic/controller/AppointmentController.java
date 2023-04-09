package com.project.clinic.controller;

import com.project.clinic.dto.AppointmentDTO;
import com.project.clinic.exceptions.BadRequestException;
import com.project.clinic.exceptions.ResourceNotFoundException;
import com.project.clinic.model.Address;
import com.project.clinic.model.Appointment;
import com.project.clinic.model.Dentist;
import com.project.clinic.model.Patient;
import com.project.clinic.service.AppointmentService;
import com.project.clinic.service.DentistService;
import com.project.clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DentistService dentistService;
    @Autowired
    private PatientService patientService;

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody AppointmentDTO app) {
        appointmentService.createAppointment(app);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> search(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(appointmentService.searchAppointment(id));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Appointment>> listAppointments() {

        return ResponseEntity.ok(appointmentService.listAppointments());
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody AppointmentDTO appointment) {
        appointmentService.modifyAppointment(appointment);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok("Appointment " + id + " deleted");
        } catch (ResourceNotFoundException | BadRequestException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/getByEnrollment")
    public List<Appointment> listAppointmentByEnrollment(@RequestParam Integer enrollment) {
        return appointmentService.searchAppointmentByEnrollment(enrollment);

    }

}
