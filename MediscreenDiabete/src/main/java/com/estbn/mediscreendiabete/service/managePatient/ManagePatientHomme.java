package com.estbn.mediscreendiabete.service.managePatient;

import org.springframework.stereotype.Service;

@Service
public class ManagePatientHomme {

    public String run(long count) {
        String result;
        switch (Math.toIntExact(count)) {
            case 0, 1, 2 -> {
                System.out.println("None");
                result = "None";
            }
            case 3, 4 -> {
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
