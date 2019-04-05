package com.vutbr.feec.IO;

import com.vutbr.feec.firm.Firm;

import java.io.*;
import java.util.Arrays;

public class Database {
    public void dbImport() {

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
