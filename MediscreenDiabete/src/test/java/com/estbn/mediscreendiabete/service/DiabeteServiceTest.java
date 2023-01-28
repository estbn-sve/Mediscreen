package com.estbn.mediscreendiabete.service;

import com.estbn.mediscreendiabete.entity.Patient;
import com.estbn.mediscreendiabete.entity.Symptoms;
import com.estbn.mediscreendiabete.service.httpRequests.HttpRequestNotes;
import com.estbn.mediscreendiabete.service.httpRequests.HttpRequestPatients;
import com.estbn.mediscreendiabete.service.managePatient.ManagePatientFemme;
import com.estbn.mediscreendiabete.service.managePatient.ManagePatientHomme;
import com.estbn.mediscreendiabete.service.managePatient.ManagePatientOlder30Years;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiabeteServiceTest {

    @InjectMocks
    private DiabeteService service;
    @Mock
    private HttpRequestPatients httpRequestPatients;
    @Mock
    private HttpRequestNotes httpRequestNotes;
    @Mock
    private ManagePatientFemme managePatientFemme;
    @Mock
    private ManagePatientHomme managePatientHomme;
    @Mock
    private ManagePatientOlder30Years managePatientOlder30Years;


    @Test
    void getDiabeteByIdPatient() {
        Patient patient = new Patient();
        patient.setLastName("LastName");
        patient.setAge(29);
        patient.setSex("M");
        patient.setId(1);
        List<String> symptoms = List.of("blablablablab", "zzzzzzzzzz");
        when(httpRequestPatients.httpById(1)).thenReturn(patient);
        when(httpRequestNotes.run(patient.getLastName())).thenReturn(symptoms);
        when(managePatientHomme.run(0)).thenReturn("none");
        Assertions.assertEquals(service.diabeteByIDPatient(1), List.of("none"));
    }

    @Test
    void getDiabeteByFamilyNamePatient() {
        Patient patient = new Patient();
        patient.setLastName("LastName");
        patient.setAge(29);
        patient.setSex("M");
        patient.setId(1);
        List<String> symptoms = List.of("blablablablab", "zzzzzzzzzz");
        when(httpRequestPatients.httpByFamilyName("LastName")).thenReturn(patient);
        when(httpRequestNotes.run("LastName")).thenReturn(symptoms);
        when(managePatientHomme.run(0)).thenReturn("none");
        Assertions.assertEquals(service.diabeteByFamilyName(patient.getLastName()), List.of("none"));
    }

}