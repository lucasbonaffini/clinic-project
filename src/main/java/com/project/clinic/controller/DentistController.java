package com.project.clinic.controller;

import com.project.clinic.dto.DentistDTO;
import com.project.clinic.exceptions.BadRequestException;
import com.project.clinic.exceptions.ResourceNotFoundException;
import com.project.clinic.model.Address;
import com.project.clinic.model.Dentist;
import com.project.clinic.model.Patient;
import com.project.clinic.service.DentistService;
import com.project.clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/dentists")
public class DentistController {
    @Autowired
    private DentistService dentistService;

    @GetMapping()
    public ResponseEntity<Collection<DentistDTO>> listDentist() {
        return ResponseEntity.ok(dentistService.getAll());
    }

    @GetMapping("/{enrollment}")
    public ResponseEntity<DentistDTO> search(@PathVariable Integer enrollment) {
        try {
            return ResponseEntity.ok(dentistService.searchDentist(enrollment));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody DentistDTO den) {
        dentistService.createDentist(den);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PutMapping()
    public ResponseEntity<?> update(@RequestBody DentistDTO dentist) {
        dentistService.modifyDentist(dentist);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @DeleteMapping("/{enrollment}")
    public ResponseEntity<?> delete(@PathVariable Integer enrollment){
        try {
            dentistService.deleteDentist(enrollment);
            return ResponseEntity.ok("Dentist " + enrollment + " deleted");
        } catch (ResourceNotFoundException | BadRequestException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping("/getLastnameLike")
    public Set<DentistDTO> listDentistsWithLastnameLike(@RequestParam String lastname) {
        return dentistService.getDentistsWithLastnameLike(lastname);
    }
}
