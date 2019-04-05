package com.vutbr.feec.employee;

import com.vutbr.feec.firm.TypeOfJob;

public class Designer extends Employee {

    private static final int TARIFF = 250;

    public Designer(String firstName, String secondName) {
        super(firstName, secondName);
        employeeType = EmployeeType.DESIGNER;
        canDoTypeOfJobs.add(TypeOfJob.DESIGN_JOB);
        canDoTypeOfJobs.add(TypeOfJob.TECHNICAL_JOB);
        this.tariff = TARIFF;
    }

}
