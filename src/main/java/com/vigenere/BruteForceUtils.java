package com.vigenere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BruteForceUtils {
    private static BruteForceUtils instance;
    private VigenereCipher vigenereCipher;
    private CharUtils charUtils;
    private String cipherText;

    private BruteForceUtils() {
        charUtils = CharUtils.getInstance();
        vigenereCipher = VigenereCipher.getInstance();
    }

    public static BruteForceUtils getInstance() {
        if (instance == null) instance = new BruteForceUtils();
        return instance;
    }

    private String generateStringValueByNumber(long value) {
        if (value < 27) {
            return Character.toString((char) (value + 96));
        } else {
            if (value % 26 == 0) {
                return generateStringValueByNumber((value / 26) - 1) + generateStringValueByNumber((value % 26) + 1);
            } else {
                return generateStringValueByNumber(value / 26) + generateStringValueByNumber(value % 26);
            }
        }
    }

    private boolean isDecipherValueValid(String checkValue, String key) {
        return vigenereCipher.getPlainText(cipherText, key).contains(checkValue);
    }

    public String findKeyByCheckValue(String cipherText, String checkValue) {
        long startTime = System.currentTimeMillis();
        String key = "";
        this.cipherText = cipherText;
        long generator = 1;
        boolean isNotFound = true;
        while (isNotFound) {
            String tmpKey = generateStringValueByNumber(generator);
            System.out.print("[INFO]: test key - " + tmpKey);
            if (!isDecipherValueValid(checkValue, tmpKey)) {
                generator++;
                System.out.println(" - not valid");
            } else {
                System.out.println(" - valid");
                key = tmpKey;
                isNotFound = false;
                long timeSpent = System.currentTimeMillis() - startTime;
                System.out.println("[RESULT]: key was found - " + key);
                System.out.println("[STATISTIC]: time - " + timeSpent + " ms");
                System.out.println("[STATISTIC]: number of attempts - " + generator);
            }
        }
        return key;
    }

    // try to do IOC test
    @Deprecated
    public void computeIndexOfCoincidence(String text) {
        int res = 0;
        char[] charText = text.toCharArray();
        int maxLength = charText.length;
        System.out.println("max length can be: " + maxLength);

        // ioc - start length = 2
        int countOfSymbolsFromText = charUtils.getCountOfCharsFromAlphabet(text);
        for (int i = 2; i <= maxLength; i++) {
            char[][] matrixOfBlocks = new char[countOfSymbolsFromText + 1][i];
            //write blocks to matrix
            int column = 0;
            int row = 0;
            for (char currentSymbol : charText) {
                if (charUtils.isFromAlphabet(currentSymbol)) {
                    matrixOfBlocks[row][column++] = charUtils.charToLower(currentSymbol);
                    if (column == i) {
                        column = 0;
                        row++;
                    }
                }
            }

            //count iOC
            List<Double> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                Map<Character, Integer> countOfSymbols = new HashMap<>();
                for (int k = 0; k < (countOfSymbolsFromText + 1); k++) {
                    char currentSymbol = matrixOfBlocks[k][j];
                    if (charUtils.isFromAlphabet(currentSymbol)) {
                        if (countOfSymbols.containsKey(currentSymbol)) {
                            int oldValue = countOfSymbols.get(currentSymbol);
                            countOfSymbols.replace(currentSymbol, oldValue, ++oldValue);
                        } else {
                            countOfSymbols.put(currentSymbol, 1);
                        }
                    }
                }
                double iOC = 0;
                for (char key : countOfSymbols.keySet()) {
                    long count = (long) countOfSymbols.get(key);
                    iOC += (count * (count - 1));
                }
                list.add(iOC / ((long) countOfSymbolsFromText * ((long) countOfSymbolsFromText - 1)));
            }

            int c = 0;
            for (double v : list) {
                //System.out.println("key length: " + i + " iOC-" + c++ + "=" + v);
                //if (v > 0.055 && v < 0.07) {
                if (v != 0.0) {
                    System.out.println(v);
                    res = i;
                    //System.out.println("HACKED for "+ i);
                }
            }


        }
        System.out.println("HACKED RESULT: " + res);
    }
}
