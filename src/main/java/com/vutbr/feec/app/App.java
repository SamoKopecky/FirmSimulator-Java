package com.vutbr.feec.app;

import com.vutbr.feec.console_interface.ConsoleInterface;
import com.vutbr.feec.employee.EmployeeType;
import com.vutbr.feec.firm.TypeOfJob;
import com.vutbr.feec.firm.Firm;

public class App {
    public static void main(String[] args) {
        Firm firm = new Firm();
        firm.addEmployee("Peter", "Copecky", EmployeeType.ASSISTANT);
        firm.addEmployee("Peter", "Bpecky", EmployeeType.DESIGNER);
        firm.addEmployee("Peter", "Aopecky", EmployeeType.TECHNICIAN);
        firm.addEmployee("Peter", "Dopecky", EmployeeType.CEO);
        firm.addEmployee("Peter", "eopecky", EmployeeType.ASSISTANT);
        firm.addEmployee("Peter", "Eopecky", EmployeeType.ASSISTANT);
//        firm.addJob(TypeOfJob.ADMINISTRATIVE_JOB, 6, null);
        ConsoleInterface consoleInterface = new ConsoleInterface(firm);
        consoleInterface.mainLoop();
    }
}
