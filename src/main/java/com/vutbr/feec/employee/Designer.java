package com.vutbr.feec.employee;

public class Designer extends Employee {
    public Designer(String firstName, String secondName) {
        super(firstName, secondName);
        setOfJobs.add(Job.DESIGN_JOB);
        setOfJobs.add(Job.TECHNICAL_JOB);
    }

}
