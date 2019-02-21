package com.vigenere;

public class CharUtils {
    private static CharUtils instance;

    private CharUtils() {
    }

    public static CharUtils getInstance() {
        if (instance == null) instance = new CharUtils();
        return instance;
    }

    public boolean isFromAlphabet(char c) {
        return ((int) c < 123 && (int) c > 96) || ((int) c > 64 && (int) c < 91);
    }

    public boolean isUpper(char c) {
        return (int) c > 64 && (int) c < 91;
    }

    public boolean isLower(char c) {
        return (int) c < 123 && (int) c > 96;
    }

    public char charToLower(char c) {
        return (char) ((int) c + 32);
    }

    public char charToUpper(char c) {
        return (char) ((int) c - 32);
    }

    public int getCountOfCharsFromAlphabet(String text) {
        int res = 0;
        for(Character ch: text.toCharArray()) {
            if(isFromAlphabet(ch)) {
                ++res;
            }
        }
        return res;
    }
}
