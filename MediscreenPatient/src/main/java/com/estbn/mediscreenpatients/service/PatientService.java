package com.estbn.mediscreenpatients.service;

import com.estbn.mediscreenpatients.model.Patient;
import com.estbn.mediscreenpatients.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public List<Patient> getAllPatients(){
        return repository.findAll();
    }

    public Patient getPatient(Integer id){
        return repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with getPatient "+ id));
    }

    public Patient addPatient(Patient patient){
        if(!repository.existsById(patient.getId())){
            return repository.save(patient);

        } else {
            return repository.findById(patient.getId()).orElseThrow(() ->
                    new NoSuchElementException("Error with addPatient " + patient.getId()));
        }
    }

    public Patient putPatient(Patient currentPatient, final Integer id){
        if (repository.existsById(id)){
            repository.save(currentPatient);
            return  currentPatient;
        }else {
            return repository.findById(id).orElseThrow(() ->
                    new NoSuchElementException("Error with addPatient " + currentPatient.getId()));
        }
    }

    public Patient deletePatien (final Integer id){
        Patient p = repository.findById(id).orElseThrow(()->
                new NoSuchElementException("Error with deleteBidList "+id));
        Patient copy = Patient.builder()
                .id(p.getId())
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .sex(p.getSex())
                .address(p.getAddress())
                .dateOfBirth(p.getDateOfBirth())
                .phoneNumber(p.getPhoneNumber())
                .build();
        repository.delete(p);
        return copy;
    }
}
