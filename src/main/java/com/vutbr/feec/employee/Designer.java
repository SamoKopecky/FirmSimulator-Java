package com.vutbr.feec.employee;

public class Designer extends Employee {

    private static final int TARIFF = 250;

    public Designer(String firstName, String secondName) {
        super(firstName, secondName);
        canDoTypeOfJobs.add(TypeOfJob.DESIGN_JOB);
        canDoTypeOfJobs.add(TypeOfJob.TECHNICAL_JOB);
        this.tariff = TARIFF;
    }

}
