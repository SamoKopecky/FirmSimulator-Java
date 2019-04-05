package com.vutbr.feec.firm;

import com.vutbr.feec.employee.Employee;

public enum TypeOfJob {
    ADMINISTRATIVE_JOB, TECHNICAL_JOB, DESIGN_JOB;

    public String action(Employee employee) {
        if (this.equals(TECHNICAL_JOB)) {
            return "technicka praca";
        }
        if (this.equals(DESIGN_JOB)) {
            return "design praca";
        }
        if (this.equals(ADMINISTRATIVE_JOB)) {
            return employee.toString();
        } else {
            return "error";
        }
    }
}
