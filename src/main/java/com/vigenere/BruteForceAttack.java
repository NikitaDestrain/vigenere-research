package com.vigenere;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BruteForceAttack {
    public static void main(String[] args) {
        System.out.println("  ____             _         ______                                         \n" +
                " |  _ \\           | |       |  ____|                                        \n" +
                " | |_) |_ __ _   _| |_ ___  | |__ ___  _ __ ___ ___                         \n" +
                " |  _ <| '__| | | | __/ _ \\ |  __/ _ \\| '__/ __/ _ \\                        \n" +
                " | |_) | |  | |_| | ||  __/ | | | (_) | | | (_|  __/                        \n" +
                " |____/|_|__ \\__,_|\\__\\___| |_|  \\___/|_|  \\___\\___|       _                \n" +
                " \\ \\    / (_)                                     (_)     | |               \n" +
                "  \\ \\  / / _  __ _  ___ _ __   ___ _ __ ___    ___ _ _ __ | |__   ___ _ __  \n" +
                "   \\ \\/ / | |/ _` |/ _ \\ '_ \\ / _ \\ '__/ _ \\  / __| | '_ \\| '_ \\ / _ \\ '__| \n" +
                "    \\  /  | | (_| |  __/ | | |  __/ | |  __/ | (__| | |_) | | | |  __/ |    \n" +
                "     \\/   |_|\\__, |\\___|_| |_|\\___|_|  \\___|  \\___|_| .__/|_| |_|\\___|_|    \n" +
                "              __/ |                                 | |                     \n" +
                "             |___/                                  |_|                   ");


        VigenereCipher vc = VigenereCipher.getInstance();
        String content = "";
        try {
            String inFullPath = Constants.FILE_PATH_IN + "/" + "brute-force-demo";
            //String inFullPath = Constants.FILE_PATH_IN + "/" + Constants.FILE_NAME;
            System.out.println("input filepath: " + inFullPath);
            content = new Scanner(new File(inFullPath)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Input file was not found! Please, move it to plaintext directory and fix Constants Class :)");
        }
        BruteForceUtils bfu = BruteForceUtils.getInstance();
        Scanner scanner = new Scanner(System.in);
        String key;
        System.out.print("key: ");
        key = scanner.next();
        scanner.close();
        System.out.println();

        String cipherText = vc.getCipherText(content, key);
        String checkValue = "[#!$CHECK VALUE$!#]";
        String bruteForcedKey = bfu.findKeyByCheckValue(cipherText, checkValue);

        /*
        System.out.println();
        System.out.println("Cipher text: ");
        System.out.println("-------------start-----------");
        System.out.println(cipherText);
        System.out.println("--------------end------------");

        System.out.println();
        System.out.printf("Plain text deciphered by key \'%s\': \n", key);
        System.out.println("-------------start-----------");
        System.out.println(vc.getPlainText(cipherText, bruteForcedKey));
        System.out.println("--------------end------------");
        */
    }
}
