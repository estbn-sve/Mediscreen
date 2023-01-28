package com.estbn.mediscreendiabete.controller;

import com.estbn.mediscreendiabete.service.DiabeteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/diabete")
@CrossOrigin("http://localhost:4200")
public class DiabeteController {


    private final String requestMapping ="/diabete";

    @Autowired
    private DiabeteService service;

    @GetMapping("/id/{id}")
    public List<String> diabeteByIDPatient(@PathVariable Integer id){
        log.info("GET "+requestMapping+"/"+id);
        return service.diabeteByIDPatient(id);
    }

    @GetMapping("/familyName/{familyName}")
    public List<String> diabeteByFamilyName(@PathVariable String familyName){
        log.info("GET "+requestMapping+"/"+familyName);
        return service.diabeteByFamilyName(familyName);
    }

}
