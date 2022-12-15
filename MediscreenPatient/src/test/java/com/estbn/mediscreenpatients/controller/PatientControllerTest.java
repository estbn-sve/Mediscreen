package com.estbn.mediscreenpatients.controller;

import com.estbn.mediscreenpatients.entity.Patient;
import com.estbn.mediscreenpatients.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PatientController.class)
@ContextConfiguration
class PatientControllerTest {
    @Autowired
    public MockMvc mockMvc;
    @MockBean
    public PatientService service;

    final private String requestMapping = "/patient/";

    //getAll
    @Test
    void getAllPatients() throws Exception {
        when(service.getAllPatients()).thenReturn(new ArrayList<>());
        mockMvc.perform(get(requestMapping))
                .andExpect(status().isOk());
    }

    //getPatient
    @Test
    void getPatient_shouldReturnOk() throws Exception {
        when(service.getPatient(any())).thenReturn(new Patient());
        mockMvc.perform(get(requestMapping+"1"))
                .andExpect(status().isOk());
    }
    @Test
    public void getPatient_shouldReturnNotFound() throws Exception{
        when(service.getPatient(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(get(requestMapping+"1"))
                .andExpect(status().isNotFound());
    }

    //addPatient
    @Test
    void addPatient_shouldReturnOk() throws Exception {
        when(service.addPatient(any())).thenReturn(new Patient());
        mockMvc.perform(post(requestMapping).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    void addPatient_shouldReturnNotFound() throws Exception {
        when(service.addPatient(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(post(requestMapping).contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }

    //putPatient
    @Test
    void putPatient_shouldReturnOk() throws Exception {
        when(service.putPatient(any(),any())).thenReturn(new Patient());
        mockMvc.perform(put(requestMapping+"1").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isOk());
    }
    @Test
    void putPatient_shouldReturnNotFound() throws Exception {
        when(service.putPatient(any(),any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(put(requestMapping+"1").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound());
    }

    //delete
    @Test
    public void deleteBidList_shouldReturnOk() throws Exception{
        when(service.deletePatien(any())).thenReturn(new Patient());
        mockMvc.perform(delete(requestMapping+"1"))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteBidList_shouldReturnNotFound() throws Exception{
        when(service.deletePatien(any())).thenThrow(new NoSuchElementException());
        mockMvc.perform(delete(requestMapping+"1"))
                .andExpect(status().isNotFound());
    }
}