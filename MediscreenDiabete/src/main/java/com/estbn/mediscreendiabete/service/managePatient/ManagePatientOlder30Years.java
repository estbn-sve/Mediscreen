package com.estbn.mediscreendiabete.service.managePatient;

import org.springframework.stereotype.Service;

@Service
public class ManagePatientOlder30Years {

    public String run(long count) {
        String result;
        switch (Math.toIntExact(count)) {
            case 0, 1 -> {
                System.out.println("None");
                result = "None";
            }
            case 2, 3, 4, 5 -> {
                System.out.println("Borderline");
                result = "Borderline";
            }
            case 6, 7 -> {
                System.out.println("In Danger");
                result = "In Danger";
            }
            default -> {
                System.out.println("Early onset");
                result = "Early onset";
            }
        }
        return result;
    }
}
