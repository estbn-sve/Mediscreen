package com.estbn.mediscreendiabete.service;

import com.estbn.mediscreendiabete.entity.Patient;
import com.estbn.mediscreendiabete.service.httpRequests.HttpRequestNotes;
import com.estbn.mediscreendiabete.service.httpRequests.HttpRequestPatients;
import com.estbn.mediscreendiabete.service.managePatient.ManagePatientFemme;
import com.estbn.mediscreendiabete.service.managePatient.ManagePatientHomme;
import com.estbn.mediscreendiabete.service.managePatient.ManagePatientOlder30Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiabeteService {

    @Autowired
    private ManagePatientFemme managePatientFemme;
    @Autowired
    private ManagePatientHomme managePatientHomme;
    @Autowired
    private ManagePatientOlder30Years managePatientOlder30Years;
    @Autowired
    private HttpRequestNotes httpRequestNotes;
    @Autowired
    private HttpRequestPatients httpRequestPatients;

    private final List<String> diabeteSymptoms = List.of(
            "Hémoglobine A1C",
            "Microalbumine",
            "Taille",
            "Poids",
            "Fumeur",
            "Anormal",
            "Cholestérol",
            "Vertige",
            "Rechute",
            "Réaction",
            "Anticorps");

    public List<String> diabeteByIDPatient(Integer id) {
        System.out.println("start diabete by ID");
        Patient patient = httpRequestPatients.httpById(id);
        List<String> symptoms = httpRequestNotes.run(patient.getLastName());
        return List.of(run(symptoms, patient));
    }

    public List<String> diabeteByFamilyName(String familyName) {
        System.out.println("start diabete by FamilyName");
        Patient patient = httpRequestPatients.httpByFamilyName(familyName);
        List<String> symptoms = httpRequestNotes.run(familyName);
        return List.of(run(symptoms, patient));
    }

    private String run(List<String> symptomsPatient, Patient patient) {
        String result = new String();
        var count = canProcess(symptomsPatient);
        if (patient.getAge() < 30) {
            switch (patient.getSex()) {
                case "F" -> {
                    System.out.println("Manage Patient femme");
                    result = managePatientFemme.run(count);
                }
                case "M" -> {
                    System.out.println("Manage Patient homme");
                    result = managePatientHomme.run(count);
                }
                default -> {
                    System.err.println("Patient sexe is not good ! " + patient.getSex());
                }
            }
        } else if (patient.getAge() >= 30) {
            result = managePatientOlder30Years.run(count);
        }
        return result;
    }

    private int canProcess(List<String> symptoms) {
        var count = 0;
        for (String diabeteSymptom : diabeteSymptoms){
            if (symptoms.stream().anyMatch(symptom -> symptom.toLowerCase().contains(diabeteSymptom.toLowerCase()))){
                count++;
            }
        }
        return count;
    }
}
