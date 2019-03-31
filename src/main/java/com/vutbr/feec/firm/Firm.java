package com.vutbr.feec.firm;

import com.vutbr.feec.employee.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Firm {
    private List<Employee> listOfEmployees;
    private List<Job> listOfJobs;

    public Firm() {
        listOfJobs = new ArrayList<>();
        listOfEmployees = new ArrayList<>();
    }

    public boolean addEmployee(String firstName, String secondName, String position) {
        switch (position) {
            case "Assistant":
                listOfEmployees.add(new Assistant(firstName, secondName));
                return true;
            case "Designer":
                listOfEmployees.add(new Designer(firstName, secondName));
                return true;
            case "Technician":
                listOfEmployees.add(new Technician(firstName, secondName));
                return true;
            case "CEO":
                Employee employee = new CEO(firstName, secondName);
                if (!listOfEmployees.contains(employee)) {
                    listOfEmployees.add(employee);
                    return true;
                } else {
                    System.out.println("COE uz exisuje");
                    employee.decrementId();
                    return false;
                }
            default:
                return false;
        }
    }

    public void addJob(TypeOfJob typeOfJob, int duration) {
        List<Employee> capableEmployees = new ArrayList<>();
        for (Employee employee : listOfEmployees) {
            if (employee.getCanDoTypeOfJobs().contains(typeOfJob)) {
                capableEmployees.add(employee);
            }
        }
        capableEmployees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getTariff() - o2.getTariff();
            }
        });

        splitWork(capableEmployees, duration, typeOfJob);

    }

    private void splitWork(List<Employee> capableEmployees, int duration, TypeOfJob typeOfJob) {
        int tariff = 0;
        List<Employee> workingEmployees = new ArrayList<>();
        int maxWork = 0;

        for (Employee employee : capableEmployees) {
            int size = employee.getListOfJobs().size();
            if (size > maxWork) {
                maxWork = size;
            }
        }
        int indexToCheck = 0;
        for (int i = 0; i <= maxWork; i++) {
            for (Employee employee : capableEmployees) {
                if (employee.getListOfJobs().size() == i && workingEmployees.isEmpty()) {
                    workingEmployees.add(employee);
                    tariff = employee.getTariff();
                    indexToCheck = i;
                } else if (employee.getListOfJobs().size() == indexToCheck && employee.getTariff() == tariff
                        && !workingEmployees.contains(employee)) {
                    workingEmployees.add(employee);
                }
            }
        }

        Job job = new Job(duration, typeOfJob, workingEmployees.size(), workingEmployees);
        listOfJobs.add(job);
        for (Employee employee : workingEmployees) {
            employee.getListOfJobs().add(job);
        }
    }

    public void doJob(int index, int duration) {
        int temp = listOfJobs.get(index).doJob(duration);
        if (temp < 0) {
            listOfJobs.remove(index);
        }
        System.out.println(temp);
    }

    public List<Employee> getListOfEmployees() {
        return listOfEmployees;
    }

    public List<Job> getListOfJobs() {
        return listOfJobs;
    }

    @Override
    public String toString() {
        return "Firm{" +
                "listOfEmployees=\n" + listOfEmployees +
                '}';
    }
}
