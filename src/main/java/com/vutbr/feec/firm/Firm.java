package com.vutbr.feec.firm;

import com.vutbr.feec.employee.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Firm implements Serializable {
    private List<Employee> listOfEmployees;
    private List<Job> listOfJobs;

    public Firm() {
        listOfJobs = new ArrayList<>();
        listOfEmployees = new ArrayList<>();
    }

    public boolean addEmployee(String firstName, String secondName, EmployeeType employeeType) {
        switch (employeeType) {
            case ASSISTANT:
                listOfEmployees.add(new Assistant(firstName, secondName));
                return true;
            case DESIGNER:
                listOfEmployees.add(new Designer(firstName, secondName));
                return true;
            case TECHNICIAN:
                listOfEmployees.add(new Technician(firstName, secondName));
                return true;
            case CEO:
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

    public int getMonthlyExpenses() {
        int expenses = 0;
        for (Job job : listOfJobs) {
            expenses += job.getDuration() * job.getWorkingEmployees().get(0).getTariff();
        }
        return expenses;
    }

    public boolean addJob(TypeOfJob typeOfJob, int duration, Job jobToReplace) {
        if (jobToReplace != null) {
            for (Job job : listOfJobs) {
                if (job.equals(jobToReplace)) {
                    for (Employee employee : listOfEmployees) {
                        employee.removeJob(job);
                    }
                    listOfJobs.remove(job);
                    break;
                }
            }
        }

        int tariff = 0;
        int maxWork = 0;
        List<Employee> workingEmployees = new ArrayList<>();
        List<Employee> capableEmployees = new ArrayList<>();
        Job job;

        for (Employee employee : listOfEmployees) {
            if (employee.getCanDoTypeOfJobs().contains(typeOfJob) && employee.isActive()) {
                capableEmployees.add(employee);
            }
        }
        capableEmployees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getTariff() - o2.getTariff();
            }
        });

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
        /*
           TODO vydelit poctom kolegou na praci, alebo to dat do novej metody to job triedy
           TODO triedenie podla id alebo mena pri vypise
           TODO zadavat hodiny alebo mesiac
           TODO databaza
           TODO Jednotkovy test
         */
        for (Employee employee : workingEmployees) {
            if (employee.getWorkingHours() + duration > employee.getContractLength()) {
                workingEmployees.remove(employee);
            }
        }

        if (workingEmployees.isEmpty()) {
            return false;
        }

        if (jobToReplace != null) {
            job = new Job(duration, typeOfJob, workingEmployees, jobToReplace.getId());
        } else {
            job = new Job(duration, typeOfJob, workingEmployees);
        }

        listOfJobs.add(job);
        for (Employee employee : workingEmployees) {
            employee.getListOfJobs().add(job);
        }
        return true;
    }

    /*public void doJob(int index, int duration) {
        if (listOfJobs.get(index).doJob(duration) < 0) {
            listOfJobs.remove(index);
        }
    }*/

    public void removeEmployee(int id) {
        Employee employeeToRemove = listOfEmployees.get(id);
        listOfEmployees.remove(id);
        for (Job job : employeeToRemove.getListOfJobs()) {
            this.addJob(job.getTypeOfJob(), job.getDuration(), job);
            if (employeeToRemove.getListOfJobs().isEmpty()) {
                return;
            }
        }
    }

    public void makeEmployeeSick(int id) {
        Employee sickEmployee = listOfEmployees.get(id);
        sickEmployee.setActive(false);
        for (Job job : sickEmployee.getListOfJobs()) {
            this.addJob(job.getTypeOfJob(), job.getDuration(), job);
            if (sickEmployee.getListOfJobs().isEmpty()) {
                return;
            }
        }
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
