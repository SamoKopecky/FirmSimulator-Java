package com.vutbr.feec.main;

import com.vutbr.feec.employee.Employee;
import com.vutbr.feec.employee.TypeOfJob;
import com.vutbr.feec.firm.Firm;
import com.vutbr.feec.firm.Job;

public class App {
    public static void main(String[] args) {
        Firm firm = new Firm();

        firm.addEmployee("Peter", "Kopecky", "Assistant");
        firm.addEmployee("Peter", "Kopecky", "Designer");
        firm.addEmployee("Peter", "Kopecky", "Technician");
        firm.addEmployee("Peter", "Kopecky", "CEO");
        firm.addEmployee("Peter", "Kopecky", "Assistant");
        firm.addEmployee("Peter", "Kopecky", "Assistant");


        firm.addJob(TypeOfJob.ADMINISTRATIVE_JOB, 6, firm.getListOfJobs().size());
        for (Job job : firm.getListOfJobs()) {
            System.out.println(job);
        }
        firm.removeEmployee(0);
        for (Job job : firm.getListOfJobs()) {
            System.out.println(job);
        }

    }
}
