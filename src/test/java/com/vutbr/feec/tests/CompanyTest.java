package com.vutbr.feec.tests;

import com.vutbr.feec.employee.EmployeeType;
import com.vutbr.feec.firm.Company;
import com.vutbr.feec.firm.JobType;
import org.junit.Test;

import static org.junit.Assert.*;

public class CompanyTest {

    @Test
    public void addJob() {
        Company company = new Company();
        // Uncoment to fail test
        // company.addEmployee("test", "test", EmployeeType.CEO);
        assertEquals("not working", false, company.addJob(JobType.ADMINISTRATIVE_JOB, 40, null));
    }
}