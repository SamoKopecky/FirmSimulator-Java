package com.vutbr.feec.employee;

public class Assistant extends Employee {

    public Assistant(String firstName, String secondName) {
        super(firstName, secondName);
        setOfJobs.add(Job.ADMINISTRATIVE_JOB);
    }
}
