package com.vutbr.feec.employee;

public class Technician extends Employee {

    private static final int TARIFF = 200;

    public Technician(String firstName, String secondName) {
        super(firstName, secondName);
        canDoTypeOfJobs.add(TypeOfJob.TECHNICAL_JOB);
        canDoTypeOfJobs.add(TypeOfJob.ADMINISTRATIVE_JOB);
        this.tariff = TARIFF;
    }
}
