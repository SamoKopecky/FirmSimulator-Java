package com.vutbr.feec.app;

import com.vutbr.feec.consoleInterface.ConsoleInterface;
import com.vutbr.feec.employee.EmployeeType;
import com.vutbr.feec.firm.Company;
import com.vutbr.feec.firm.JobType;

public class App {
    public static void main(String[] args) {
        Company company = new Company();
        createTestDB(company);
        ConsoleInterface consoleInterface = new ConsoleInterface(company);
        consoleInterface.mainLoop();
    }

    private static void createTestDB(Company company) {
        company.addEmployee("Peter", "Copecky", EmployeeType.ASSISTANT);
        company.addEmployee("Peter", "Bpecky", EmployeeType.DESIGNER);
        company.addEmployee("Peter", "Aopecky", EmployeeType.TECHNICIAN);
        company.addEmployee("Peter", "Dopecky", EmployeeType.CEO);
        company.addEmployee("Peter", "eopecky", EmployeeType.ASSISTANT);
        company.addEmployee("Peter", "Eopecky", EmployeeType.ASSISTANT);
        company.addJob(JobType.ADMINISTRATIVE_JOB, 6, null);
    }
}
