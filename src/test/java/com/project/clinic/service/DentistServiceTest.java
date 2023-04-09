package com.project.clinic.service;

import com.project.clinic.dto.DentistDTO;
import com.project.clinic.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistServiceTest {
    @Autowired
    private DentistService dentistService;
    private DentistDTO dentist;

    @BeforeEach
    public void setUp() {
        dentist = new DentistDTO();
        dentist.setEnrollment(1);
        dentist.setLastname("Wick");
        dentist.setName("John");
    }

    @Test
    public void testCreateDentist() throws Exception {

        dentistService.createDentist(dentist);

        assertNotNull(dentistService.searchDentist(dentist.getEnrollment()));
    }

    @Test
    public void testSearchDentist() throws Exception {

        dentistService.createDentist(dentist);

        DentistDTO foundDentist = dentistService.searchDentist(dentist.getEnrollment());

        assertEquals(dentist.getEnrollment(),foundDentist.getEnrollment());

    }

    @Test
    public void testSearchByLastName(){

        dentistService.createDentist(dentist);

        Set<DentistDTO> dentists = dentistService.getDentistsWithLastnameLike("Wi");

        boolean result = dentists.size() > 0;

        assertTrue(result);


    }
    @Test
    public void testUpdateDentist(){
        dentistService.createDentist(dentist);

        dentist.setLastname("Wick");
        dentist.setName("Keanu");

        dentistService.modifyDentist(dentist);

        assertEquals(dentist.getName(), "Keanu");

    }

    @Test
    public void testDeleteDentist() throws Exception {
        dentistService.createDentist(dentist);

        dentistService.deleteDentist(dentist.getEnrollment());

        assertThrows(ResourceNotFoundException.class, () -> dentistService.searchDentist(dentist.getEnrollment()));
    }

    @Test
    public void testSearchAll() {
        dentistService.createDentist(dentist);

        assertNotEquals(0,dentistService.getAll().size());
    }

}
