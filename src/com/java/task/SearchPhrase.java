package com.java.task;

import java.io.*;

public class SearchPhrase {

    // walk to root way
    public void walk(String path, String whatFind) throws IOException {

        File root = new File(path);
        File[] list = root.listFiles();
        for (File titleName : list) {
            if (titleName.isDirectory()) {
                walk(titleName.getAbsolutePath(), whatFind);
            } else {
                if (read(titleName.getAbsolutePath()).contains(whatFind)) {
                    System.out.println("File: " + titleName.getAbsolutePath());
                }
            }
        }
    }

    // Read file as one line
    public static String read(String fileName) {
        StringBuilder strBuider = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(
                    fileName)));
            String strInput;
            while ((strInput = in.readLine()) != null) {
                strBuider.append(strInput);
                strBuider.append("\n");
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strBuider.toString();
    }

    public static void main(String[] args) {

        SearchPhrase example = new SearchPhrase();

        try {
            example.walk("C:\\Documents and Settings\\User\\Java", "programmed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

