package com.vutbr.feec.main;

import com.vutbr.feec.firm.Firm;

public class App {
    public static void main(String[] args) {
        Firm firm = new Firm();
        firm.addEmployee("Peter", "Kopecky", "CEO");
        firm.addEmployee("Peter", "Kopecky", "Assistant");
        firm.addEmployee("Peter", "Kopecky", "Assistant");
        firm.addEmployee("Peter", "Kopecky", "Designer");
        firm.addEmployee("Peter", "Kopecky", "Designer");
        firm.addEmployee("Peter", "Kopecky", "CEO");
        firm.addEmployee("Peter", "Kopecky", "Technician");
        firm.addEmployee("Peter", "Kopecky", "Technician");

        System.out.println(firm);
    }
}
