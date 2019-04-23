package com.vutbr.feec.employee;

import java.io.Serializable;

public enum EmployeeType implements Serializable {
    ASSISTANT("Assitant"),
    CEO("Riaditel"),
    DESIGNER("Vyvojovy pracovnik"),
    TECHNICIAN("Technicky pracovnik");

    private String desc;

    EmployeeType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}