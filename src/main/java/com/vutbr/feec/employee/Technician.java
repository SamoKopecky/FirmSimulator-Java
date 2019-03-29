package com.vutbr.feec.employee;

public class Technician extends Employee {
    public Technician(String firstName, String secondName) {
        super(firstName, secondName);
        setOfJobs.add(Job.TECHNICAL_JOB);
        setOfJobs.add(Job.ADMINISTRATIVE_JOB);
    }
}
