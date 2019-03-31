package com.vutbr.feec.firm;

import com.vutbr.feec.employee.Employee;
import com.vutbr.feec.employee.TypeOfJob;

import java.util.List;

public class Job {
    private int duration;
    private TypeOfJob typeOfJob;
    private int workEfficiency;
    private int id;
    private List<Employee> workingEmployees;
    private static int idIncrement = 0;

    Job(int duration, TypeOfJob typeOfJob, int workEfficiency, List<Employee> workingEmployees) {
        this.workingEmployees = workingEmployees;
        this.id = idIncrement;
        idIncrement++;
        this.duration = duration;
        this.typeOfJob = typeOfJob;
        this.workEfficiency = workEfficiency;
    }

    public int doJob(int duration) {
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
        return moneyUsed;
    }

    public boolean decreseJobDuration(int duration) {
        this.duration -= duration;
        return this.duration <= 0;
    }

    public TypeOfJob getTypeOfJob() {
        return typeOfJob;
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

    public int getWorkEfficiency() {
        return workEfficiency;
    }

    @Override
    public String toString() {
        return "Job{" +
                "duration=" + duration +
                ", typeOfJob=" + typeOfJob +
                ", workEfficiency=" + workEfficiency +
                ", id=" + id +
                ", workingEmployees=" + workingEmployees +
                '}';
    }
}
