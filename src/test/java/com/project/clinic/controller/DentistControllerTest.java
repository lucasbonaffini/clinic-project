package com.project.clinic.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.clinic.dto.DentistDTO;
import com.project.clinic.exceptions.GlobalExceptionsHandler;
import com.project.clinic.service.DentistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DentistController.class)
@AutoConfigureMockMvc(addFilters = false)
class DentistControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private DentistService dentistService;

    @Autowired
    private DentistController dentistController;

    private DentistDTO den;


    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(dentistController)
                .setControllerAdvice(GlobalExceptionsHandler.class)
                .build();
        den = new DentistDTO(10032,"Wick","John");
    }

    @Test
    @DisplayName("Search all dentist: Success")
    void searchAllDentists() throws Exception {
        dentistService.createDentist(den);
        Collection<DentistDTO> dentists = dentistService.getAll();
        Mockito.when(dentistService.getAll()).thenReturn(dentists);
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/dentists")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

}
