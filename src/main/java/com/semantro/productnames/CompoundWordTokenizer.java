package com.semantro.productnames;

import com.semantro.productnames.ProductDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is a part of the package com.semantro.com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-09.
 */
public class CompoundWordTokenizer {
    /**
     * Given word.
     */
    private final String compoundWord;
    private final List<String> tokens;
    
    /**
     * Default constructor.
     *
     * @param compoundWord
     */
    public CompoundWordTokenizer(String compoundWord) {
        this.compoundWord = compoundWord;
        this.tokens = new ArrayList<>();
    }
    
    /**
     * Returns the list of tokenized expanded words.
     *
     * @return listOfTokenWords
     */
    public List<String> getWords() {
        List<String> words = Arrays.asList(this.compoundWord.split("-"));
        words.forEach(this::breakCompoundWord);
        this.tokens.add(String.join("", words));
        /**
         * Identify all the sub-words too.
         * (word longer than length of 3.)
         */
        words.forEach(word -> {
            this.tokens.addAll(getSubWords(word));
        });
        return this.tokens.stream().distinct().collect(Collectors.toList());
    }
    
    /**
     * Get all the valid words from the compound word.
     */
    public List<String> getTokens() {
        List<String> words = Arrays.asList(this.compoundWord.split("-"));
        List<String> validWords = new ArrayList<>();
        words.forEach(subWord -> validWords.addAll(getSubWords(subWord)));
        validWords.add(String.join("", words));
        return validWords.stream().distinct().collect(Collectors.toList());
    }
    
    /**
     * Get all the sub-words present in the dictionary.
     */
    private List<String> getSubWords(String givenWord) {
        List<String> subWords = new ArrayList<>();
        for (int beginIndex = 0; beginIndex < givenWord.length(); beginIndex++) {
            for (int currentIndex = beginIndex + 1; currentIndex <= givenWord.length(); currentIndex++) {
                String subWord = givenWord.substring(beginIndex, currentIndex);
                if (isValidSubWord(subWord)) subWords.add(subWord);
            }
        }
        return subWords;
    }
    
    /**
     * Check if the sub is valid to add in the tokenized list.
     */
    private boolean isValidSubWord(String word) {
        return word.length() >= States.SUB_WORD_THRESHOLD && ProductDictionary.isPresent(word);
    }
    
    /**
     * Dynamic programming version for breaking word problem.
     * Gives preference to longer words over splits
     * e.g peanutbutter with dict{pea nut butter peanut} it would result in
     * peanut butter instead of pea nut butter.
     */
    private void breakCompoundWord(String word) {
        int T[][] = new int[word.length()][word.length()];
        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[i].length; j++) {
                T[i][j] = -1; //-1 indicates string between i to j cannot be split
            }
        }
        
        //Fill up the matrix in bottom up manner
        for (int l = 1; l <= word.length(); l++) {
            for (int i = 0; i < word.length() - l + 1; i++) {
                int j = i + l - 1;
                String str = word.substring(i, j + 1);
                //if string between i to j is in dictionary T[i][j] is true
                if (ProductDictionary.isPresent(str)) {
                    T[i][j] = i;
                    continue;
                }
                //Find a k between i+1 to j such that T[i][k-1] && T[k][j] are both true
                for (int k = i + 1; k <= j; k++) {
                    if (T[i][k - 1] != -1 && T[k][j] != -1) {
                        T[i][j] = k;
                        break;
                    }
                }
            }
        }
        
        /**
         * Do not collect if it cannot break the compound word.
         */
        if (T[0][word.length() - 1] == -1) return;
        
        //Collect separated word from string if possible.
        int i = 0;
        int j = word.length() - 1;
        while (i < j) {
            int k = T[i][j];
            if (i == k) {
                this.tokens.add(word.substring(i, j + 1));
                break;
            }
            this.tokens.add(word.substring(i, k));
            i = k;
        }
    }
    
}
