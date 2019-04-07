package com.vutbr.feec.firm;

import com.vutbr.feec.employee.Employee;

import java.io.Serializable;

public enum JobType implements Serializable {
    ADMINISTRATIVE_JOB, TECHNICAL_JOB, DESIGN_JOB;

    public String action(Employee employee) {
        if (this.equals(TECHNICAL_JOB)) {
            String vowels = "aeiouy";
            String fullName = employee.getFirstName() + employee.getSecondName();
            int count = 0;
            for (char c : fullName.toCharArray()) {
                if(vowels.contains(String.valueOf(c))) {
                    count++;
                }
            }
            return "Moje meno ma " + count + " samohlasok.";
        }
        if (this.equals(DESIGN_JOB)) {
            String fullName = employee.getFirstName() + " " + employee.getSecondName();
            String toReturn = "";
            for (int i = fullName.length() - 1; i >= 0; i--) {
                toReturn = toReturn.concat(String.valueOf(fullName.charAt(i)));
            }
            return toReturn;
        }
        if (this.equals(ADMINISTRATIVE_JOB)) {
            return employee.toString();
        } else {
            return "error";
        }
    }
}
