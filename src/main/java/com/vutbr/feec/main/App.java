package com.vutbr.feec.main;

import com.vutbr.feec.console_interface.ConsoleInterface;
import com.vutbr.feec.employee.EmployeeType;
import com.vutbr.feec.firm.TypeOfJob;
import com.vutbr.feec.firm.Firm;

public class App {
    public static void main(String[] args) {
        Firm firm = new Firm();
        firm.addEmployee("Peter", "Kopecky", EmployeeType.ASSISTANT);
        firm.addEmployee("Peter", "Kopecky", EmployeeType.DESIGNER);
        firm.addEmployee("Peter", "Kopecky", EmployeeType.TECHNICIAN);
        firm.addEmployee("Peter", "Kopecky", EmployeeType.CEO);
        firm.addEmployee("Peter", "Kopecky", EmployeeType.ASSISTANT);
        firm.addEmployee("Peter", "Kopecky", EmployeeType.ASSISTANT);
        firm.addJob(TypeOfJob.ADMINISTRATIVE_JOB, 6, null);
        ConsoleInterface consoleInterface = new ConsoleInterface(firm);
        consoleInterface.mainLoop();
    }
}
