package com.vutbr.feec.main;

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
            System.out.println(job + "\n");
        }
        firm.makeEmployeeSick(0);
        firm.removeEmployee(4);
        for (Job job : firm.getListOfJobs()) {
            System.out.println(job + "\n");
        }
        firm.getListOfEmployees().get(0).setActive(true);
        firm.addJob(TypeOfJob.ADMINISTRATIVE_JOB, 6, firm.getListOfJobs().size());
        firm.doJob(0,6);
        for (Job job : firm.getListOfJobs()) {
            System.out.println(job + "\n");
        }

    }
}
