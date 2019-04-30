package com.vutbr.feec.io;

import com.vutbr.feec.firm.Company;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {
    public Company dbImport(File file) {
        Company company = new Company();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            company = (Company) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("can't find file or can't write to object");
        }
        return company;
    }

    public String getCurrDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void dbExport(Company company, File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(company);
        } catch (IOException e) {
            System.out.println("can't write to file");
        }
    }
}
