package com.vutbr.feec.employee;

import com.vutbr.feec.firm.TypeOfJob;

public class Assistant extends Employee {

    private static final int TARIFF = 150;

    public Assistant(String firstName, String secondName) {
        super(firstName, secondName);
        employeeType = EmployeeType.ASSISTANT;
        canDoTypeOfJobs.add(TypeOfJob.ADMINISTRATIVE_JOB);
        this.tariff = TARIFF;
    }
}
