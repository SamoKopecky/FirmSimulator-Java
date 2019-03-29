package com.vutbr.feec.employee;

public class CEO extends Employee {

    public CEO(String firstName, String secondName) {
        super(firstName, secondName);
        setOfJobs.add(Job.ADMINISTRATIVE_JOB);
        setOfJobs.add(Job.DESIGN_JOB);
        setOfJobs.add(Job.TECHNICAL_JOB);
    }
}
