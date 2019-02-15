package com.vigenere;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // prepare out file name
        String encFileName = "cipher-" + Constants.FILE_NAME;

        // get file content
        String content = "";
        try {
            String inFullPath = Constants.FILE_PATH_IN + "/" + Constants.FILE_NAME;
            content = new Scanner(new File(inFullPath)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Input file was not found! Please, move it to plaintext directory and fix Constants Class :)");
        }

        // cipher time
        VigenereCipher vc = VigenereCipher.getInstance();
        String cipherText = vc.getCipherText(content);

        // prepare for writing result
        String outFullPath = Constants.FILE_PATH_OUT + "/" + encFileName;

        // write cipher text to file
        try (PrintWriter pw = new PrintWriter(outFullPath)) {
            pw.write(cipherText);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Output file can not be created! Please, check stack trace logs:");
            e.printStackTrace();
        }
    }
}