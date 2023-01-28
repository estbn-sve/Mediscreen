package com.estbn.mediscreendiabete.controller;

import com.estbn.mediscreendiabete.service.DiabeteService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(DiabeteController.class)
@ContextConfiguration
class DiabeteControllerTest {

    @Autowired
    public MockMvc mockMvc;
    @MockBean
    public DiabeteService service;

    final private String requestMapping = "/diabete/";

    @Test
    void getPatientById_shouldReturnOk() throws Exception {
        when(service.diabeteByIDPatient(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"id/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getPatientByFamilyName_shouldReturnOk() throws Exception {
        when(service.diabeteByFamilyName(any())).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get(requestMapping+"familyName/Ferguson"))
                .andExpect(status().isOk());
    }
}