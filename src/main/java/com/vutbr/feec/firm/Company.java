package com.vutbr.feec.firm;

import com.vutbr.feec.employee.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Company implements Serializable {
    private List<Employee> listOfEmployees;
    private List<Job> listOfJobs;

    public Company() {
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
                    employee.decrementId();
                    return false;
                }
            default:
                return false;
        }
    }

    public int getMonthlyExpenses() {
        int expenses = 0;
        int duration;
        int numberOfEmployees;

        for (Job job : listOfJobs) {
            duration = job.getDuration();
            numberOfEmployees = job.getWorkingEmployees().size();
            if (duration > 31) {
                duration = 31;
            }
            expenses += duration * job.getWorkingEmployees().get(0).getTariff() * numberOfEmployees;
        }
        return expenses;
    }

    public boolean addJob(JobType jobType, int duration, Job jobToReplace) {
        if (listOfEmployees.isEmpty() || duration == 0) {
            return false;
        }

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

        List<Employee> workingEmployees = getAvailableEmployees(jobType);
        Job job;

        boolean toReturn = true;
        removeOverworkedEmployees(duration, workingEmployees);
        if (workingEmployees.isEmpty()) {
            boolean allTested = true;
            for (Employee employee : listOfEmployees) {
                if (!employee.isTested() && employee.getCanDoTypeOfJobs().contains(jobType)) {
                    allTested = false;
                }
            }
            if (allTested) return false;
            toReturn = addJob(jobType, duration, jobToReplace);
        }
        workingEmployees.forEach(employee -> employee.setTested(false));
        if (!toReturn) {
            return false;
        }

        if (jobToReplace != null) {
            job = new Job(duration / workingEmployees.size(), jobType, workingEmployees, jobToReplace.getId());
        } else {
            job = new Job(duration / workingEmployees.size(), jobType, workingEmployees);
        }

        listOfJobs.add(job);
        workingEmployees.forEach(employee -> employee.getListOfJobs().add(job));

        return true;
    }

    private List<Employee> getAvailableEmployees(JobType jobType) {
        int tariff = 0;
        int maxWork = 0;
        List<Employee> workingEmployees = new ArrayList<>();
        List<Employee> capableEmployees = new ArrayList<>();

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
        workingEmployees.forEach(employee -> employee.setTested(true));

        return workingEmployees;
    }

    private void removeOverworkedEmployees(int duration, List<Employee> workingEmployees) {
        List<Employee> copy = new ArrayList<>();
        for (Employee employee : workingEmployees) {
            if (employee.getWorkingHours() + (duration / workingEmployees.size()) > employee.getContractLength()) {
                copy.add(employee);
            }
        }
        copy.forEach((copy::remove));
    }

    public boolean removeEmployee(int id) throws NullPointerException {
        Employee employeeToRemove = getObjectByID(listOfEmployees, id);
        listOfEmployees.remove(employeeToRemove);
        boolean toReturn = true;
        for (Job job : employeeToRemove.getListOfJobs()) {
            toReturn = this.addJob(job.getJobType(), job.getDuration(), job);
            if (employeeToRemove.getListOfJobs().isEmpty()) {
                break;
            }
        }
        return toReturn;
    }

    public boolean makeEmployeeSick(int id) throws NullPointerException {
        Employee sickEmployee = getObjectByID(listOfEmployees, id);
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

    public <T> T getObjectByID(List<T> list, int id) throws NullPointerException {
        for (T object : list) {
            if (object.hashCode() == id) {
                return object;
            }
        }
        throw new NullPointerException();
    }

    public List<Employee> getListOfEmployees() {
        return listOfEmployees;
    }

    public List<Job> getListOfJobs() {
        return listOfJobs;
    }


}
