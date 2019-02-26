package com.vigenere;

import java.util.HashMap;
import java.util.Map;

public class VigenereCipher {

    private static VigenereCipher instance;
    private CharUtils charUtils;
    private Map<Character, Integer> alphabetMap;
    private char[] alphabet;

    private VigenereCipher() {
        prepareAlphabet();
        charUtils = CharUtils.getInstance();
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

    private int getSymbolShift(int incorrectShift) {
        return incorrectShift - 26;
    }

    private int getDecipherSymbolShift(int incorrectShift) {
        return 26 + incorrectShift;
    }

    private char[] getFixedKey(String key) {
        StringBuilder sb = new StringBuilder();
        for (char ch : key.toLowerCase().toCharArray()) {
            if (charUtils.isFromAlphabet(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString().toCharArray();
    }

    public String getCipherText(String text) {
        long startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        char[] plainText = text.toCharArray();
        char prevChar = 'a';
        //StringBuilder key = new StringBuilder();
        for (char currentSymbol : plainText) {
            if (charUtils.isFromAlphabet(currentSymbol)) {
                //key.append(prevChar);
                boolean isUpperCase = charUtils.isUpper(currentSymbol);
                int sumOfInd = alphabetMap.get(charUtils.charToLower(currentSymbol))
                        + alphabetMap.get(prevChar);
                int shift = sumOfInd > 25 ? getSymbolShift(sumOfInd) : sumOfInd;
                char cC = alphabet[shift];
                sb.append(isUpperCase ? charUtils.charToUpper(cC) : cC);
                prevChar = cC;
            } else {
                sb.append(currentSymbol);
            }
        }
        System.out.println("  done");
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("time: " + timeSpent + " ms");
        //System.out.println("generated key: " + key.toString());
        return sb.toString();
    }

    public String getCipherText(String text, String key) {
        StringBuilder sb = new StringBuilder();
        char[] plainText = text.toCharArray();
        int ketLength = key.length() - 1;
        int keyCounter = 0;
        char[] keyText = getFixedKey(key);
        for (char currentSymbol : plainText) {
            if (charUtils.isFromAlphabet(currentSymbol)) {
                boolean isUpperCase = charUtils.isUpper(currentSymbol);
                if (keyCounter > ketLength) {
                    keyCounter = 0;
                }
                char keyChar = keyText[keyCounter++];
                int sumOfInd = alphabetMap.get(charUtils.charToLower(currentSymbol))
                        + alphabetMap.get(keyChar);
                int shift = sumOfInd > 25 ? getSymbolShift(sumOfInd) : sumOfInd;
                char cC = alphabet[shift];
                sb.append(isUpperCase ? charUtils.charToUpper(cC) : cC);
            } else {
                sb.append(currentSymbol);
            }
        }
        return sb.toString();
    }

    public String getPlainText(String text, String key) {
        StringBuilder sb = new StringBuilder();
        char[] cipherText = text.toCharArray();
        char[] keyText = key.toLowerCase().toCharArray();
        int j = 0;
        for (char currentSymbol : cipherText) {
            if (j >= keyText.length) {
                j = 0;
            }
            if (charUtils.isFromAlphabet(currentSymbol)) {
                boolean isUpperCase = charUtils.isUpper(currentSymbol);
                int sumOfInd = alphabetMap.get(charUtils.charToLower(currentSymbol))
                        - alphabetMap.get(keyText[j++]);
                int shift = sumOfInd < 0 ? getDecipherSymbolShift(sumOfInd) : sumOfInd;
                char cC = alphabet[shift];
                sb.append(isUpperCase ? charUtils.charToUpper(cC) : cC);
            } else {
                sb.append(currentSymbol);
            }
        }
        return sb.toString();
    }
}