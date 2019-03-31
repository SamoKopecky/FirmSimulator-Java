package com.vutbr.feec.employee;

public class CEO extends Employee{

    private static final int TARIFF = 350;

    public CEO(String firstName, String secondName) {
        super(firstName, secondName);
        canDoTypeOfJobs.add(TypeOfJob.ADMINISTRATIVE_JOB);
        canDoTypeOfJobs.add(TypeOfJob.DESIGN_JOB);
        canDoTypeOfJobs.add(TypeOfJob.TECHNICAL_JOB);
        this.tariff = TARIFF;
    }
}
