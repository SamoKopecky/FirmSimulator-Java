package com.vutbr.feec.firm;

import com.vutbr.feec.employee.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
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

    public boolean addJob(JobType jobType, int duration, Job jobToReplace) {
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
            if (employee.getCanDoTypeOfJobs().contains(jobType) && employee.isActive()) {
                capableEmployees.add(employee);
            }
        }
        capableEmployees.sort(Comparator.comparingInt(Employee::getTariff));

        for (Employee employee : capableEmployees) {
            int size = employee.getListOfJobs().size();
            if (size > maxWork) {
                maxWork = size;
            }
        }

        int indexToCheck = 0;
        for (int i = 0; i <= maxWork; i++) {
            for (Employee employee : capableEmployees) {
                if (employee.getListOfJobs().size() == i && workingEmployees.isEmpty() && !employee.isTested()) {
                    workingEmployees.add(employee);
                    tariff = employee.getTariff();
                    indexToCheck = i;
                } else if (employee.getListOfJobs().size() == indexToCheck && employee.getTariff() == tariff
                        && !workingEmployees.contains(employee) && !employee.isTested()) {
                    workingEmployees.add(employee);
                }
            }
        }
        /*
           TODO databaza
           TODO Jednotkovy test
         */
        for (Employee employee : workingEmployees) {
            employee.setTested(true);
        }
        removeOverworkedEmployees(duration, workingEmployees);
        if (workingEmployees.isEmpty()) {
            addJob(jobType, duration, jobToReplace);
            return false;
        }

        if (jobToReplace != null) {
            job = new Job(duration, jobType, workingEmployees, jobToReplace.getId());
        } else {
            job = new Job(duration, jobType, workingEmployees);
        }

        listOfJobs.add(job);
        for (Employee employee : workingEmployees) {
            employee.getListOfJobs().add(job);
        }
        return true;
    }

    private void removeOverworkedEmployees(int duration, List<Employee> workingEmployees) {
        List<Employee> copy = new ArrayList<>();
        for (Employee employee : workingEmployees) {
            if (employee.getWorkingHours() + (duration / workingEmployees.size()) > employee.getContractLength()) {
                copy.add(employee);
            }
        }
        for (Employee employee : copy) {
            workingEmployees.remove(employee);
        }
    }

    /*public void doJob(int index, int duration) {
        if (listOfJobs.get(index).doJob(duration) < 0) {
            listOfJobs.remove(index);
        }
    }*/

    public boolean removeEmployee(int id) {
        Employee employeeToRemove = listOfEmployees.get(id);
        listOfEmployees.remove(id);
        boolean toReturn = true;
        for (Job job : employeeToRemove.getListOfJobs()) {
            toReturn = this.addJob(job.getJobType(), job.getDuration(), job);
            if (employeeToRemove.getListOfJobs().isEmpty()) {
                break;
            }
        }
        return toReturn;
    }

    public boolean makeEmployeeSick(int id) {
        Employee sickEmployee = listOfEmployees.get(id);
        sickEmployee.setActive(false);
        boolean toReturn = true;
        for (Job job : sickEmployee.getListOfJobs()) {
            toReturn = this.addJob(job.getJobType(), job.getDuration(), job);
            if (sickEmployee.getListOfJobs().isEmpty()) {
                break;
            }
        }
        return toReturn;
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
