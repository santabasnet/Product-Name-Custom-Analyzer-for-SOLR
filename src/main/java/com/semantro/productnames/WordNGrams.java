package com.semantro.productnames;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class is a part of the package com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2021-02-19.
 */
public class WordNGrams {
    
    /**
     * Max Gram.
     */
    private static final int MAX_GRAM = 5;
    
    /**
     * Min Gram.
     */
    private static final int MIN_GRAM = 4;
    
    /**
     * Token Name: Word only.
     */
    private static final int STRING_WORD = 0;
    
    /**
     * Token Name: Number only.
     */
    private static final int NUMBER_WORD = 1;
    
    /**
     * Token Name: Number and characters mixed word.
     */
    private static final int MIXED_WORD = 2;
    
    /**
     * Given word.
     */
    private String givenWord;
    
    /**
     * Default constructor.
     */
    public WordNGrams(String givenWord) {
        this.givenWord = givenWord.trim();
    }
    
    /**
     * Returns the list of n-grams based on the defined criteria.
     *
     * @return listOfGrams.
     */
    public List<String> tokens() {
        return tokenStream().collect(Collectors.toList());
    }
    
    /**
     * Returns the token stream of word grams.
     *
     * @return tokenStream
     */
    public Stream<String> tokenStream() {
        if (isEligible()) return listOfGrams();
        else return new ArrayList<String>().stream();
    }
    
    /**
     * Perform the NGram separation.
     */
    private Stream<String> listOfGrams() {
        Stream<String> allGrams = IntStream.rangeClosed(MIN_GRAM, MAX_GRAM)
                .mapToObj(this::singleGram)
                .flatMap(Function.identity());
        return allGrams;
    }
    
    private Stream<String> singleGram(int size) {
        List<String> grams = new ArrayList<>();
        int begin = 0;
        int end = size;
        while (end <= this.givenWord.length()) {
            grams.add(this.givenWord.substring(begin, end));
            begin++;
            end++;
        }
        return grams.stream();
    }
    
    /**
     * Identifies if the given word has to be converted in the list of n-grams tokens or not.
     *
     * @return true if it is a string type.
     */
    private Boolean isEligible() {
        return tokenTypeOfWord() == STRING_WORD && this.givenWord.length() > MIN_GRAM;
    }
    
    /**
     * Returns the token type.
     */
    private int tokenTypeOfWord() {
        
        int state = 0;
        int tokenType = MIXED_WORD;
        
        for (char entryChar : this.givenWord.toCharArray()) {
            switch (state) {
                case 0:
                    if (Character.isDigit(entryChar)) {
                        state = 1;
                        tokenType = NUMBER_WORD;
                    } else if (Character.isAlphabetic(entryChar)) {
                        state = 2;
                        tokenType = STRING_WORD;
                    } else state = 3;
                    break;
                
                case 1:
                    if (Character.isDigit(entryChar)) state = 1;
                    else if (Character.isAlphabetic(entryChar)) state = 2;
                    else state = 3;
                    break;
                
                case 2:
                    if (Character.isAlphabetic(entryChar)) {
                        state = 2;
                        tokenType = STRING_WORD;
                    } else {
                        state = 3;
                        tokenType = MIXED_WORD;
                    }
                    break;
                
                case 3:
                    state = 3;
                    tokenType = MIXED_WORD;
                    break;
            }
        }
        
        return tokenType;
    }
    
    
}
