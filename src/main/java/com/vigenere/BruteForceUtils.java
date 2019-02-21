package com.vigenere;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BruteForceUtils {
    private static BruteForceUtils instance;
    private CharUtils charUtils;

    private BruteForceUtils() {
        charUtils = CharUtils.getInstance();
    }

    public static BruteForceUtils getInstance() {
        if (instance == null) instance = new BruteForceUtils();
        return instance;
    }

    public void computeIndexOfCoincidence(String text) {
        char[] charText = text.toCharArray();
        int maxLength = charText.length;
        System.out.println("max length can be: " + maxLength);

        // ioc - start length = 2
        List<Integer> indexesOfCoincidence = new ArrayList<>();
        int countOfSymbolsFromText = charUtils.getCountOfCharsFromAlphabet(text);
        for (int i = 2; i <= maxLength; i++) {
            /*Map<Character, Integer> countOfSymbols = new HashMap<>();
            for (int j = 0; j < i; j++) {
                char curSymb = charText[j];
                if (charUtils.isFromAlphabet(curSymb)) {
                    if (countOfSymbols.get(curSymb) == null) {
                        countOfSymbols.put(curSymb, 1);
                    } else {
                        int oldValue = countOfSymbols.get(curSymb);
                        countOfSymbols.replace(curSymb, oldValue, ++oldValue);
                    }
                }
            }
            int iOC = 0;
            for (Character key : countOfSymbols.keySet()) {
                int count = countOfSymbols.get(key);
                iOC += count * (count - 1) / i * (i - 1);
            }
            indexesOfCoincidence.add(iOC);
            System.out.println("iOC for length: " + i + " is " + iOC);*/

            char[][] matrixOfBlocks = new char[countOfSymbolsFromText + 1][i];
            //write blocks to matrix
            //count iOC
            //write it to map if close to 0,667

        }
        System.out.println(indexesOfCoincidence);
    }
}
