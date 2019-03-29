package com.vutbr.feec.employee;

import java.util.HashSet;
import java.util.Set;

public abstract class Employee {
    private static int idCounter = 0;
    protected int id;
    protected String firstName;
    protected String secondName;
    protected Set<Job> setOfJobs;

    public Employee(String firstName, String secondName) {
        setOfJobs = new HashSet<>();
        this.id = idCounter;
        idCounter++;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public void decrementId() {
        idCounter--;
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
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", class='" + this.getClass().getSimpleName() + '\'' +
                "}\n";
    }
}
