package com.vutbr.feec.employee;

public class Assistant extends Employee {

    private static final int TARIFF = 150;

    public Assistant(String firstName, String secondName) {
        super(firstName, secondName);
        canDoTypeOfJobs.add(TypeOfJob.ADMINISTRATIVE_JOB);
        this.tariff = TARIFF;
    }
}
