package com.vigenere;

import java.util.HashMap;
import java.util.Map;

public class VigenereCipher {

    private static VigenereCipher instance;
    private Map<Character, Integer> alphabetMap;
    private char[] alphabet;

    private VigenereCipher() {
        prepareAlphabet();
    }

    public static VigenereCipher getInstance() {
        if (instance == null) instance = new VigenereCipher();
        return instance;
    }

    private void prepareAlphabet() {
        alphabetMap = new HashMap<>();
        alphabet = Constants.ENG_ALPHABET.toCharArray();
        for (int i = 0; i < alphabet.length; i++) {
            alphabetMap.put(alphabet[i], i);
        }
    }

    private boolean isFromAlphabet(char c) {
        return ((int) c < 123 && (int) c > 96) || ((int) c > 64 && (int) c < 91);
    }

    private boolean isUpper(char c) {
        return (int) c > 64 && (int) c < 91;
    }

    private boolean isLower(char c) {
        return (int) c < 123 && (int) c > 96;
    }

    private char charToLower(char c) {
        return (char) ((int) c + 32);
    }

    private char charToUpper(char c) {
        return (char) ((int) c - 32);
    }

    private int getSymbolShift(int incorrectShift) {
        return incorrectShift - 26;
    }

    public String getCipherText(String text) {
        StringBuilder sb = new StringBuilder();
        char[] plainText = text.toCharArray();
        char prevChar = 'a';
        for (char currentSymbol : plainText) {
            if (isFromAlphabet(currentSymbol)) {
                boolean isUpperCase = isUpper(currentSymbol);
                int sumOfInd = alphabetMap.get(isUpperCase ? charToLower(currentSymbol) : currentSymbol)
                        + alphabetMap.get(prevChar);
                int shift = sumOfInd > 25 ? getSymbolShift(sumOfInd) : sumOfInd;
                char cC = alphabet[shift];
                sb.append(isUpperCase ? charToUpper(cC) : cC);
                prevChar = cC;
            } else {
                sb.append(currentSymbol);
            }
        }
        return sb.toString();
    }
}