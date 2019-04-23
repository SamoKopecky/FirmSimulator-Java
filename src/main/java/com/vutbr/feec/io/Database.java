package com.vutbr.feec.io;

import com.vutbr.feec.firm.Firm;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Database {
    public Firm dbImport(File file) {
        Firm firm = new Firm();
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            firm = (Firm) ois.readObject();
            fis.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("can't find file or can't write to object");
        }
        return firm;
    }

    public String getCurrDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void dbExport(Firm firm, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(firm);
            fos.close();
            oos.close();
        } catch (IOException e) {
            System.out.println("can't write to file");
        }
    }
}