package com.estbn.mediscreendiabete.service.managePatient;

import org.springframework.stereotype.Service;

@Service
public class ManagePatientFemme {

    public String run(long count) {
        String result;
        switch (Math.toIntExact(count)) {
            case 0,1,2,3 -> {
                System.out.println("None");
                result = "None";
            }
            case 4,5,6 -> {
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
