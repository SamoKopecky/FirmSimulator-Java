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
        int workDonePerPerson = this.duration;
        if(this.duration - duration > 0) {
            workDonePerPerson = duration * workEfficiency;
        }
        this.duration -= duration * workEfficiency;

        int moneyUsed = 0;
        for (Employee employee : workingEmployees) {
            moneyUsed += (int) ((double)employee.getTariff() * ((double) workDonePerPerson / (double)workEfficiency));
            if (this.duration <= 0) {
                employee.getListOfJobs().remove(this);
            }
        }

        if (this.duration <= 0) {
            moneyUsed = -moneyUsed;
        }
        return moneyUsed;
    }

    public List<Employee> getWorkingEmployees() {
        return workingEmployees;
    }

    public int getWorkEfficiency() {
        return workEfficiency;
    }
}
