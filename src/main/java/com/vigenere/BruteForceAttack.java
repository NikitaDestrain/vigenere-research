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
            String inFullPath = Constants.FILE_PATH_OUT + "/" + "cipher-war-and-peace.txt";
            System.out.println("input filepath: " + inFullPath);
            content = new Scanner(new File(inFullPath)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Input file was not found! Please, move it to plaintext directory and fix Constants Class :)");
        }
        System.out.println("try to compute key length...");
        BruteForceUtils bfu = BruteForceUtils.getInstance();
        bfu.computeIndexOfCoincidence(content);
    }
}
