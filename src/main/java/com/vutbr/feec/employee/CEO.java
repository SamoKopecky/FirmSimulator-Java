package com.vutbr.feec.employee;

import com.vutbr.feec.firm.JobType;

public class CEO extends Employee {

    private static final int TARIFF = 350;

    public CEO(String firstName, String secondName) {
        super(firstName, secondName);
        employeeType = EmployeeType.CEO;
        canDoTypeOfJobs.add(JobType.ADMINISTRATIVE_JOB);
        canDoTypeOfJobs.add(JobType.DESIGN_JOB);
        canDoTypeOfJobs.add(JobType.TECHNICAL_JOB);
        this.tariff = TARIFF;
    }
}
