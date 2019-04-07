package com.vutbr.feec.employee;

import com.vutbr.feec.firm.Job;
import com.vutbr.feec.firm.JobType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Employee implements Serializable {
    private boolean tested = false;
    private static int idCounter = 0;
    private int id;
    private String firstName;
    private String secondName;
    EmployeeType employeeType;
    int tariff;
    private int contractLength = 20;
    private List<Job> listOfJobs;
    Set<JobType> canDoTypeOfJobs;
    private boolean active;

    public Employee(String firstName, String secondName) {
        active = true;
        listOfJobs = new ArrayList<>();
        canDoTypeOfJobs = new HashSet<>();
        this.id = idCounter;
        idCounter++;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public int getWorkingHours() {
        int temp = 0;
        for (Job job : listOfJobs) {
            temp += job.getDuration();
        }
        return temp;
    }

    public void setTested(boolean tested) {
        this.tested = tested;
    }

    public boolean isTested() {
        return tested;
    }

    public void decrementId() {
        idCounter--;
    }

    public Set<JobType> getCanDoTypeOfJobs() {
        return canDoTypeOfJobs;
    }

    public int getTariff() {
        return tariff;
    }

    public List<Job> getListOfJobs() {
        return listOfJobs;
    }

    public void setMonthlyJobDuration(int contractLength) {
        if (!(this instanceof CEO)) {
            this.contractLength = contractLength;
            if (this.contractLength <= 0) {
                active = false;
            }
        }
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public int getId() {
        return id;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getContractLength() {
        return contractLength;
    }

    public String getFirstName() {
        return firstName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String action(JobType jobType, Employee employee) {
        if (canDoTypeOfJobs.contains(jobType) && active) {
            return jobType.action(employee);
        } else {
            return "Nemoze vykonat pracu lebo je chory";
        }
    }

    public void removeJob(Job jobToRemove) {
        for (Job job : listOfJobs) {
            if (job.equals(jobToRemove)) {
                listOfJobs.remove(jobToRemove);
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Employee) {
            if (this.hashCode() == o.hashCode()) {
                return true;
            } else return o instanceof CEO;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        String toReturn = "\n\nID : " + id
                + "\n meno : " + firstName
                + "\n priezvisko : " + secondName
                + "\n pozicia : " + employeeType + "\nList prac : ";
        for (Job job : listOfJobs) {
            toReturn = toReturn.concat(" \nID : " + job.getId()
                    + "\n  pocet hodin : " + job.getDuration()
                    + "\n  typ prace : " + job.getJobType());
        }
        return toReturn;
    }
}