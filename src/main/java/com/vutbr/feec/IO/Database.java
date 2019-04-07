package com.vutbr.feec.IO;

import com.vutbr.feec.firm.Firm;

import java.io.*;
import java.util.Arrays;

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
            System.out.println("can't find file or can't write to object" + Arrays.toString(e.getStackTrace()));
        }
        return firm;
    }

    public void dbExport(Firm firm, File file) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(firm);
            fos.close();
            oos.close();
        } catch (IOException e) {
            System.out.println("can't write to file" + Arrays.toString(e.getStackTrace()));
        }
    }
}
