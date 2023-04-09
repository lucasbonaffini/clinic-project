package com.project.clinic.controller;


import com.project.clinic.dto.PatientDTO;
import com.project.clinic.exceptions.BadRequestException;
import com.project.clinic.exceptions.ResourceNotFoundException;
import com.project.clinic.service.PatientService;
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


    @GetMapping()
    public ResponseEntity<Collection<PatientDTO>> listPatients(){
        return ResponseEntity.ok(patientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> search(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(patientService.searchPatient(id));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody PatientDTO pat) {
        patientService.createPatient(pat);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody PatientDTO pat){
        patientService.modifyPatient(pat);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient " + id + " deleted");
        } catch (ResourceNotFoundException | BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getLastnameLike")
    public Set<PatientDTO> listPatientsWithLastnameLike(@RequestParam String lastname) {
        return patientService.getPatientsWithLastnameLike(lastname);
    }
}
