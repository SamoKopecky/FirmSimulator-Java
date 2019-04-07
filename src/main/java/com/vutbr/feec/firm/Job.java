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

    List<Employee> getWorkingEmployees() {
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
