package com.vutbr.feec.main;

import com.vutbr.feec.console_interface.ConsoleInterface;
import com.vutbr.feec.employee.Employee;
import com.vutbr.feec.firm.TypeOfJob;
import com.vutbr.feec.firm.Firm;
import com.vutbr.feec.firm.Job;

public class App {
    public static void main(String[] args) {
        Firm firm = new Firm();
        firm.addEmployee("Peter", "Kopecky", "assistant");
        firm.addEmployee("Peter", "Kopecky", "designer");
        firm.addEmployee("Peter", "Kopecky", "technician");
        firm.addEmployee("Peter", "Kopecky", "ceo");
        firm.addEmployee("Peter", "Kopecky", "assistant");
        firm.addEmployee("Peter", "Kopecky", "assistant");
        firm.addJob(TypeOfJob.ADMINISTRATIVE_JOB, 6, null);
        ConsoleInterface consoleInterface = new ConsoleInterface(firm);
        consoleInterface.mainLoop();



/*        firm.addJob(TypeOfJob.ADMINISTRATIVE_JOB, 6, null);
        for (Job job : firm.getListOfJobs()) {
            System.out.println(job + "\n");
        }
//        firm.makeEmployeeSick(0);
        firm.removeEmployee(4);
        for (Job job : firm.getListOfJobs()) {
            System.out.println(job + "\n");
        }
        consoleInterface.mainLoop();
        for (Employee employee : firm.getListOfEmployees()) {
            System.out.println(employee);
        }*/
    }
}
