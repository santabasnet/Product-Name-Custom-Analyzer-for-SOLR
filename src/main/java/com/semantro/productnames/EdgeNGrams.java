package com.semantro.productnames;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This class is a part of the package com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-13.
 */
public class EdgeNGrams {
    
    /**
     * Minimum size.
     */
    private static final int MIN_SIZE = 4;
    
    /**
     * Given word.
     */
    private final String givenWord;
    
    /**
     * Default constructor.
     */
    public EdgeNGrams(String givenWord) {
        this.givenWord = givenWord;
    }
    
    /**
     * Find all the Edge NGrams.
     */
    public List<String> tokenize() {
        if (isNotValidWord()) return new ArrayList<>();
        else return getEdgeTokenizedWords();
    }
    
    /**
     * Check validity.
     */
    private boolean isNotValidWord() {
        return givenWord == null || givenWord.length() < MIN_SIZE;
    }
    
    /**
     * Check if the given word is eligible to have edge tokens.
     */
    private boolean eligibleToEdgeTokens(String word) {
        return word.length() >= MIN_SIZE && !Character.isDigit(word.charAt(0));
    }
    
    /**
     * Tokenize here.
     */
    private List<String> prefixTokens(String word) {
        List<String> nGrams = new ArrayList<>();
        IntStream.rangeClosed(MIN_SIZE, word.length()).forEach(index -> nGrams.add(word.substring(0, index)));
        return nGrams;
    }
    
    /**
     * Find all the edge tokenized words.
     */
    private List<String> getEdgeTokenizedWords() {
        return new NumberTokenizer(this.givenWord).getTokens().stream()
                .filter(this::eligibleToEdgeTokens)
                .flatMap(word -> new WordNGrams(word).tokenStream())
                .collect(Collectors.toList());
    }
    
}
