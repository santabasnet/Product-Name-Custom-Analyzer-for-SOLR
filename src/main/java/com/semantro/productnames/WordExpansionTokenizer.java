package com.semantro.productnames;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
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
public class WordExpansionTokenizer {
    /**
     * Given Word.
     */
    private final String compoundWord;
    private final List<String> tokens;
    private int state;
    
    /**
     * Default constructor.
     *
     * @param compoundWord
     */
    public WordExpansionTokenizer(String compoundWord) {
        this.compoundWord = compoundWord;
        this.tokens = new ArrayList<>();
        this.state = States.ALPHA;
    }
    
    /**
     * Returns the expanded list of tokens;
     *
     * @return listOfTokens
     */
    public List<String> getTokens() {
        buildTokens(new StringCharacterIterator(this.compoundWord));
        this.tokens.add(this.compoundWord);
        return this.tokens.stream().distinct().collect(Collectors.toList());
    }
    
    /**
     * Perform tokenization.
     */
    private void buildTokens(CharacterIterator iterator) {
        StringBuilder wordBuffer = new StringBuilder();
        while (iterator.current() != CharacterIterator.DONE) {
            char character = iterator.current();
            switch (this.state) {
                case States.ALPHA:
                    if (Character.isDigit(character)) {
                        wordBuffer = addTokens(wordBuffer);
                        this.state = States.NUM;
                    }
                    break;
                case States.NUM:
                    if (Character.isAlphabetic(character)) {
                        wordBuffer = addTokens(wordBuffer);
                        this.state = States.ALPHA;
                    }
                    break;
            }
            wordBuffer.append(character);
            iterator.next();
        }
        if (wordBuffer.length() > 0) this.tokens.add(wordBuffer.toString());
    }
    
    /**
     * Add to tokens.
     */
    private StringBuilder addTokens(StringBuilder wordBuffer) {
        if (wordBuffer.length() > 0) {
            this.tokens.add(wordBuffer.toString());
            return new StringBuilder();
        } else return wordBuffer;
    }
}