package com.vutbr.feec.firm;

import com.vutbr.feec.employee.Employee;

import java.io.Serializable;

public enum JobType implements Serializable {
    ADMINISTRATIVE_JOB("Administrativa"),
    TECHNICAL_JOB("Tehcnicka praca"),
    DESIGN_JOB("Vyvoj");

    private String desc;

    JobType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public String action(Employee employee) {
        if (this.equals(TECHNICAL_JOB)) {
            String vowels = "aeiouy";
            String fullName = employee.getFirstName() + employee.getSecondName();
            int count = 0;
            for (char c : fullName.toCharArray()) {
                if (vowels.contains(String.valueOf(c))) {
                    count++;
                }
            }
            return "Moje meno ma " + count + " samohlasok.";
        }
        if (this.equals(DESIGN_JOB)) {
            String fullName = employee.getFirstName() + " " + employee.getSecondName();
            StringBuilder sb = new StringBuilder();
            for (int i = fullName.length() - 1; i >= 0; i--) {
                sb.append(fullName.charAt(i));
            }
            return sb.toString();
        }
        if (this.equals(ADMINISTRATIVE_JOB)) {
            return employee.toString();
        } else {
            return "error";
        }
    }
}
