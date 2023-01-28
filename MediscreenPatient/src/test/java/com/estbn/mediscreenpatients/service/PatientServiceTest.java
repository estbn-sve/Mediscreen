package com.estbn.mediscreenpatients.service;

import com.estbn.mediscreenpatients.entity.DTO.MiniPatient;
import com.estbn.mediscreenpatients.entity.Patient;
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
        Patient patientResult = service.deletePatien(1);
        Assertions.assertEquals(patient.getFirstName(), patientResult.getFirstName());
    }
    @Test
    void deletePatient_shouldThrowNoSuchElement() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () ->
        service.deletePatien(1));
    }

    //getPatientById
    @Test
    void getPatientbyId_shouldReturnOk() {
        Patient patient = Patient.builder()
                .id(1)
                .lastName("Ferguson")
                .sex("M")
                .dateOfBirth(LocalDate.of(1994,06,12))
                .build();

        MiniPatient miniPatient = new MiniPatient();
        miniPatient.setLastName(patient.getLastName());
        miniPatient.setId(patient.getId());
        miniPatient.setSex(patient.getSex());
        miniPatient.setAge(LocalDate.now().getYear() - patient.getDateOfBirth().getYear());
        when(repository.findById(any())).thenReturn(Optional.of(patient));
        Assertions.assertEquals(service.getPatientById(1),miniPatient);
    }
    @Test
    void getPatientbyId_shouldThrowNoSuchElement() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () ->
                service.getPatientById(1));
    }
    //getPatientByFamilyName
    @Test
    void getPatientbyFamilyName_shouldReturnOk() {
        Patient patient = Patient.builder()
                .id(1)
                .lastName("Ferguson")
                .sex("M")
                .dateOfBirth(LocalDate.of(1994,06,12))
                .build();

        MiniPatient miniPatient = new MiniPatient();
        miniPatient.setLastName(patient.getLastName());
        miniPatient.setId(patient.getId());
        miniPatient.setSex(patient.getSex());
        miniPatient.setAge(LocalDate.now().getYear() - patient.getDateOfBirth().getYear());
        when(repository.findByLastName(any())).thenReturn(Optional.of(patient));
        Assertions.assertEquals(service.getPatientByFamilyName("Ferguson"),miniPatient);
    }
    @Test
    void getPatientbyFamilyName_shouldThrowNoSuchElement() {
        when(repository.findByLastName(any())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () ->
                service.getPatientByFamilyName("Ferguson"));
    }
}