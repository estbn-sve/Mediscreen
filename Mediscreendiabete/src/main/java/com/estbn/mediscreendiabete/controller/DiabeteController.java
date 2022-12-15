package com.estbn.mediscreendiabete.controller;

import com.estbn.mediscreendiabete.service.DiabeteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/diabete")
@CrossOrigin("http://localhost:4200")
public class DiabeteController {


    private final String requestMapping ="/diabete";

    @Autowired
    private DiabeteService service;

//    @GetMapping("/")
//    public Symptom receivingSymptomByPatient(@RequestBody Symptom symptom){
//        log.info("");
//        return symptom;
//    }
//
//    @PostMapping("/")
//    public Diabete returnResultDiabete(){
//        return new Diabete();
//    }
}
