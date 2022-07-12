package com.semantro.productnames;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a part of the package com.semantro.com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-09.
 */
public class NumberTokenizer {
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
    public NumberTokenizer(String compoundWord) {
        this.compoundWord = compoundWord;
        this.tokens = new ArrayList<>();
        this.state = States.ALPHA;
    }
    
    /**
     * Returns the tokenized output.
     *
     * @return listOfWordTokens
     */
    public List<String> getTokens() {
        buildTokens(new StringCharacterIterator(this.compoundWord));
        return this.tokens;
    }
    
    /**
     * Build tokens.
     */
    private void buildTokens(CharacterIterator iterator) {
        StringBuilder wordBuffer = new StringBuilder();
        while (iterator.current() != CharacterIterator.DONE) {
            char character = iterator.current();
            //System.out.println("State : " + this.state + "\tChar : " + character + "\t Word : " + wordBuffer.toString());
            switch (this.state) {
                case States.ALPHA:
                    if (Character.isDigit(character)) {
                        if (wordBuffer.length() > 0)
                            this.tokens.add(wordBuffer.toString());
                        wordBuffer = new StringBuilder();
                        this.state = States.NUM;
                    }
                    wordBuffer.append(character);
                    break;
                case States.NUM:
                    if (!Character.isDigit(character))
                        this.state = States.ALPHA_NUM;
                    wordBuffer.append(character);
                    break;
                case States.ALPHA_NUM:
                    if (Character.isDigit(character)) {
                        if (wordBuffer.length() > 0) this.tokens.add(wordBuffer.toString());
                        wordBuffer = new StringBuilder();
                        this.state = States.NUM;
                    }
                    wordBuffer.append(character);
                    break;
            }
            iterator.next();
        }
        if (wordBuffer.length() > 0) this.tokens.add(wordBuffer.toString());
    }
}

