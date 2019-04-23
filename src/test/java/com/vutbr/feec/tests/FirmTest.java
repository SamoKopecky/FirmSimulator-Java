package com.vutbr.feec.tests;

import com.vutbr.feec.employee.EmployeeType;
import com.vutbr.feec.firm.Firm;
import com.vutbr.feec.firm.JobType;
import org.junit.Test;

import static org.junit.Assert.*;

public class FirmTest {

    @Test
    public void addJob() {
        Firm firm = new Firm();
        firm.addEmployee("test", "test", EmployeeType.CEO);
        assertEquals("not working", false, firm.addJob(JobType.ADMINISTRATIVE_JOB, 40, null));
    }
}