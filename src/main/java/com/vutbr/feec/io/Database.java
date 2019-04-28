package com.vutbr.feec.io;

import com.vutbr.feec.firm.Company;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {
    public Company dbImport(File file) {
        Company company = new Company();
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            company = (Company) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("can't find file or can't write to object");
        } finally {
            if (fis != null && ois != null) {
                try {
                    fis.close();
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return company;
    }

    public String getCurrDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void dbExport(Company company, File file) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(company);
        } catch (IOException e) {
            System.out.println("can't write to file");
        } finally {
            if (fos != null && oos != null) {
                try {
                    fos.close();
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
