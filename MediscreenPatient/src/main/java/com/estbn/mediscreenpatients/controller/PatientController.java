package com.estbn.mediscreenpatients.controller;

import com.estbn.mediscreenpatients.entity.Patient;
import com.estbn.mediscreenpatients.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/patient")
@CrossOrigin("http://localhost:4200")
public class PatientController {

    final String requestMapping ="/patient";

    @Autowired
    private PatientService service;

    @GetMapping("")
    public List<Patient> getAllPatients(){
        log.info("GET : "+requestMapping);
        return service.getAllPatients();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") final Integer id){
        log.info("GET : "+requestMapping+"/"+id);
        try {
            return ResponseEntity.ok(service.getPatient(id));
        } catch (NoSuchElementException e){
            log.error("GET "+requestMapping+"/"+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        log.info("POST : "+requestMapping+"/"+patient.getId());
        try {
            return ResponseEntity.ok(service.addPatient(patient));
        } catch (NoSuchElementException e){
            log.error("POST "+requestMapping+"/"+patient.getId()+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Patient> putPatient(@PathVariable("id") final Integer id, @RequestBody Patient patient){
        log.info("PUT : "+requestMapping+"/"+id);
        try {
            return ResponseEntity.ok(service.putPatient(patient, id));
        } catch (NoSuchElementException e){
            log.error("PUT "+requestMapping+"/"+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable("id") final Integer id){
        log.info("DELETE "+requestMapping+"/"+id);
        try {
            return ResponseEntity.ok(service.deletePatien(id));
        }catch (NoSuchElementException e){
            log.error("DELETE "+requestMapping+"/"+id+" Error : "+e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
