package com.vutbr.feec.firm;

import com.vutbr.feec.employee.*;

import java.util.ArrayList;
import java.util.List;

public class Firm {
    private List<Employee> listOfEmployees;

    public Firm() {
        listOfEmployees = new ArrayList<>();
    }

    public boolean addEmployee(String firstName, String secondName, String position) {
        switch (position) {
            case "Assistant":
                listOfEmployees.add(new Assistant(firstName, secondName));
                return true;
            case "Designer":
                listOfEmployees.add(new Designer(firstName, secondName));
                return true;
            case "Technician":
                listOfEmployees.add(new Technician(firstName, secondName));
                return true;
            case "CEO":
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

    @Override
    public String toString() {
        return "Firm{" +
                "listOfEmployees=\n" + listOfEmployees +
                '}';
    }
}
