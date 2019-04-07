package com.vutbr.feec.employee;

import com.vutbr.feec.firm.JobType;

public class Assistant extends Employee {

    private static final int TARIFF = 150;

    public Assistant(String firstName, String secondName) {
        super(firstName, secondName);
        employeeType = EmployeeType.ASSISTANT;
        canDoTypeOfJobs.add(JobType.ADMINISTRATIVE_JOB);
        this.tariff = TARIFF;
    }
}
