package com.vutbr.feec.employee;

import com.vutbr.feec.firm.Job;
import com.vutbr.feec.firm.TypeOfJob;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Employee implements Serializable {
    private static int idCounter = 0;
    private int id;
    private String firstName;
    private String secondName;
    EmployeeType employeeType;
    int tariff;
    private int contractLength = 271560;
    private List<Job> listOfJobs;
    Set<TypeOfJob> canDoTypeOfJobs;
    private boolean active;

    public Employee(String firstName, String secondName) {
        if (this instanceof CEO) contractLength = Integer.MAX_VALUE;
        active = true;
        listOfJobs = new ArrayList<>();
        canDoTypeOfJobs = new HashSet<>();
        this.id = idCounter;
        idCounter++;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public void decrementId() {
        idCounter--;
    }

    public Set<TypeOfJob> getCanDoTypeOfJobs() {
        return canDoTypeOfJobs;
    }

    public int getTariff() {
        return tariff;
    }

    public List<Job> getListOfJobs() {
        return listOfJobs;
    }

    public void decreaseContractLength(int length) {
        contractLength -= length;
        if (contractLength <= 0) {
            active = false;
        }
    }

    public boolean setContractLength(int contractLength) {
        if (!(this instanceof CEO)) {
            this.contractLength = contractLength;
            if (this.contractLength <= 0) {
                active = false;
            }
            return true;
        } else {
            return false;
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

    public String action(TypeOfJob typeOfJob, Employee employee) {
        if (canDoTypeOfJobs.contains(typeOfJob) && active) {
            return typeOfJob.action(employee);
        } else {
            return "nema tuto pracu";
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
        return id * 13;
    }

    @Override
    public String toString() {
        return "\nEmployee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", class='" + this.getClass().getSimpleName() + '\'' +
                "}";
    }
}
