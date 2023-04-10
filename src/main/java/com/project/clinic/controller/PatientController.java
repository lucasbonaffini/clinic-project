package com.project.clinic.controller;


import com.project.clinic.dto.PatientDTO;
import com.project.clinic.exceptions.BadRequestException;
import com.project.clinic.exceptions.ResourceNotFoundException;
import com.project.clinic.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @Operation(summary = "Get all patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @GetMapping()
    public ResponseEntity<Collection<PatientDTO>> listPatients(){
        return ResponseEntity.ok(patientService.getAll());
    }

    @Operation(summary = "Get patient by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> search(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(patientService.searchPatient(id));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Create a patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @PostMapping()
    public ResponseEntity<?> register(@RequestBody PatientDTO pat) {
        patientService.createPatient(pat);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Update a patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody PatientDTO pat){
        patientService.modifyPatient(pat);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @Operation(summary = "Delete a patient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient " + id + " deleted");
        } catch (ResourceNotFoundException | BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    @Operation(summary = "Get a patient by lastname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success | OK"),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request", content = @Content) })
    @GetMapping("/getLastnameLike")
    public Set<PatientDTO> listPatientsWithLastnameLike(@RequestParam String lastname) {
        return patientService.getPatientsWithLastnameLike(lastname);
    }
}
