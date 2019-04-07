package com.vutbr.feec.firm;

import com.vutbr.feec.employee.Employee;

import java.io.Serializable;
import java.util.List;

public class Job implements Serializable {
    private int duration;
    private JobType jobType;
    private int id;
    private static int idCounter = 0;
    private List<Employee> workingEmployees;

    Job(int duration, JobType jobType, List<Employee> workingEmployees, int id) {
        this.workingEmployees = workingEmployees;
        this.id = id;
        this.duration = duration;
        this.jobType = jobType;
    }

    Job(int duration, JobType jobType, List<Employee> workingEmployees) {
        this.workingEmployees = workingEmployees;
        this.id = idCounter;
        idCounter++;
        this.duration = duration;
        this.jobType = jobType;
    }

    /*public int doJob(int duration) {
        int workDonePerPerson;
        int workDone = duration * workEfficiency;
        if(this.duration - workDone <= 0) {
            workDonePerPerson = this.duration / workEfficiency;
            this.duration -= workDone;
        } else {
            workDonePerPerson = duration;
            this.duration -= duration * workEfficiency;
        }

        for (Employee employee : workingEmployees) {
            employee.decreaseContractLength(workDonePerPerson);
        }

        int moneyUsed = 0;
        for (Employee employee : workingEmployees) {
            moneyUsed += (int) (employee.getTariff() * (double) workDonePerPerson);
            if (this.duration <= 0) {
                employee.getListOfJobs().remove(this);
            }
        }

        if (this.duration <= 0) {
            moneyUsed = -moneyUsed;
        }
        return moneyUsed;*/
    //}

    public void decreaseJobDuration(int duration) {
        this.duration -= duration;
    }

    public JobType getJobType() {
        return jobType;
    }

    public int getId() {
        return id;
    }

    public int getDuration() {
        return duration;
    }

    public List<Employee> getWorkingEmployees() {
        return workingEmployees;
    }

    @Override
    public String toString() {
        return "Job{" +
                "duration=" + duration +
                ", jobType=" + jobType +
                ", id=" + id +
                ", workingEmployees=" + workingEmployees +
                '}';
    }
}
