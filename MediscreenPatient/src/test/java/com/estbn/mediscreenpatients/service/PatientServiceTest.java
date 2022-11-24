package com.estbn.mediscreenpatients.service;

import com.estbn.mediscreenpatients.model.Patient;
import com.estbn.mediscreenpatients.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
    @InjectMocks
    private PatientService service;
    @Mock
    private PatientRepository repository;


    @Test
    void getAllPatients() {
        List<Patient> patient = new ArrayList<>();
        when(repository.findAll()).thenReturn(patient);
        Assertions.assertEquals(service.getAllPatients(),patient);
    }


    @Test
    void getPatient_shouldReturnOk() {
        Patient patient = new Patient();
        when(repository.findById(any())).thenReturn(Optional.of(patient));
        Assertions.assertEquals(service.getPatient(1),patient);
    }
    @Test
    void getPatient_shouldThrowNoSuchElement() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () ->
        service.getPatient(1));
    }

    @Test
    void addPatient_shouldReturnOk() {
        Patient patient = new Patient();
        patient.setId(1);
        when(repository.existsById(any())).thenReturn(false);
        when(repository.save(any())).thenReturn(patient);
        Assertions.assertEquals(service.addPatient(patient),patient);
    }
    @Test
    void addPatient_shouldThrowNoSuchElement() {
        Patient patient = new Patient();
        patient.setId(1);
        when(repository.existsById(any())).thenReturn(true);
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () ->
        service.addPatient(patient));
    }

    @Test
    void putPatient_shouldReturnOk() {
        Patient patient = new Patient();
        when(repository.existsById(any())).thenReturn(true);
        Assertions.assertEquals(service.putPatient(patient,1),patient);
    }
    @Test
    void putPatient_shouldThrowNoSuchElement() {
        Patient patient = new Patient();
        when(repository.existsById(any())).thenReturn(false);
        assertThrows(NoSuchElementException.class, () ->
        service.putPatient(patient,1));
    }

    @Test
    void deletePatient_shouldReturnOk() {
        Patient patient = new Patient();
        patient.setFirstName("Jonh");
        when(repository.findById(any())).thenReturn(Optional.of(patient));
        doNothing().when(repository).delete(any());
        Patient bidResult = service.deletePatien(1);
        Assertions.assertEquals(patient.getFirstName(), bidResult.getFirstName());
    }
    @Test
    void deletePatient_shouldThrowNoSuchElement() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () ->
        service.deletePatien(1));
    }
}