package com.vutbr.feec.employee;

import com.vutbr.feec.firm.JobType;

public class Technician extends Employee {

    private static final int TARIFF = 200;

    public Technician(String firstName, String secondName) {
        super(firstName, secondName);
        employeeType = EmployeeType.TECHNICIAN;
        canDoTypeOfJobs.add(JobType.TECHNICAL_JOB);
        canDoTypeOfJobs.add(JobType.ADMINISTRATIVE_JOB);
        this.tariff = TARIFF;
    }
}
