package com.vigenere;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // prepare out file name
        System.out.println(" __      ___                                       _       _                \n" +
                " \\ \\    / (_)                                     (_)     | |               \n" +
                "  \\ \\  / / _  __ _  ___ _ __   ___ _ __ ___    ___ _ _ __ | |__   ___ _ __  \n" +
                "   \\ \\/ / | |/ _` |/ _ \\ '_ \\ / _ \\ '__/ _ \\  / __| | '_ \\| '_ \\ / _ \\ '__| \n" +
                "    \\  /  | | (_| |  __/ | | |  __/ | |  __/ | (__| | |_) | | | |  __/ |    \n" +
                "     \\/   |_|\\__, |\\___|_| |_|\\___|_|  \\___|  \\___|_| .__/|_| |_|\\___|_|    \n" +
                "              __/ |                                 | |                     \n" +
                "             |___/                                  |_|                    ");
        String encFileName = "cipher-" + Constants.FILE_NAME;

        // get file content
        String content = "";
        try {
            String inFullPath = Constants.FILE_PATH_IN + "/" + Constants.FILE_NAME;
            System.out.println("input filepath: " + inFullPath);
            content = new Scanner(new File(inFullPath)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Input file was not found! Please, move it to plaintext directory and fix Constants Class :)");
        }

        // cipher time
        System.out.print("generating cipher text...");
        VigenereCipher vc = VigenereCipher.getInstance();
        String cipherText = vc.getCipherText(content);

        // prepare for writing result
        String outFullPath = Constants.FILE_PATH_OUT + "/" + encFileName;
        System.out.print("write result to " + outFullPath + "...");

        // write cipher text to file
        try (PrintWriter pw = new PrintWriter(outFullPath)) {
            pw.write(cipherText);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Output file can not be created! Please, check stack trace logs:");
            e.printStackTrace();
        }
        System.out.println("  done");
    }
}