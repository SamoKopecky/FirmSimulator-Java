package com.vutbr.feec.main;

import com.vutbr.feec.employee.Employee;
import com.vutbr.feec.employee.TypeOfJob;
import com.vutbr.feec.firm.Firm;

public class App {
    public static void main(String[] args) {
        Firm firm = new Firm();

        firm.addEmployee("Peter", "Kopecky", "Assistant");
        /*firm.addEmployee("Peter", "Kopecky", "Assistant");
        firm.addEmployee("Peter", "Kopecky", "Assistant");
        firm.addEmployee("Peter", "Kopecky", "Assistant");*/
        /*        firm.addEmployee("Peter", "Kopecky", "Designer");*/

        /*firm.addEmployee("Peter", "Kopecky", "Technician");*/
        firm.addEmployee("Peter", "Kopecky", "Technician");
        firm.addEmployee("Peter", "Kopecky", "CEO");
        firm.addEmployee("Peter", "Kopecky", "Assistant");
        firm.addEmployee("Peter", "Kopecky", "Assistant");

        firm.addJob(TypeOfJob.ADMINISTRATIVE_JOB, 3);

        /*for (Employee employee : firm.getListOfEmployees()) {
            if (!employee.getListOfJobs().isEmpty()) {
                System.out.println(employee.getListOfJobs().get(0).getDuration());
            }
        }*/
        //System.out.println(firm.getListOfEmployees());
        for (Employee employee : firm.getListOfEmployees()) {
            System.out.println(employee.getListOfJobs());
        }
        for (Employee e : firm.getListOfJobs().get(0).getWorkingEmployees())
            System.out.println(e);
        System.out.println(firm.getListOfJobs().get(0).getWorkEfficiency());
        firm.doJob(0, 1);
        System.out.println(firm.getListOfJobs().size());
        for (Employee employee : firm.getListOfEmployees()) {
            System.out.println(employee.getListOfJobs());
        }
    }
}
