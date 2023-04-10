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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Get all appointments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @GetMapping()
    public ResponseEntity<List<Appointment>> listAppointments() {

        return ResponseEntity.ok(appointmentService.listAppointments());
    }

    @Operation(summary = "Get appointment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> search(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(appointmentService.searchAppointment(id));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Create an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @PostMapping()
    public ResponseEntity<?> register(@RequestBody AppointmentDTO app) {
        appointmentService.createAppointment(app);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @Operation(summary = "Update an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody AppointmentDTO appointment) {
        appointmentService.modifyAppointment(appointment);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @Operation(summary = "Delete an appointment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok("Appointment " + id + " deleted");
        } catch (ResourceNotFoundException | BadRequestException e) {
            throw new RuntimeException(e);
        }

    }

    @Operation(summary = "Get an appointment by dentist enrollment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @GetMapping("/getByEnrollment")
    public List<Appointment> listAppointmentByEnrollment(@RequestParam Integer enrollment) {
        return appointmentService.searchAppointmentByEnrollment(enrollment);

    }

}
