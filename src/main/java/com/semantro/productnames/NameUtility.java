package com.semantro.productnames;

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
public class NameUtility {
    
    /**
     * Splits a given word into the set of words considering number
     * inside are the delimiters.
     * Examples:
     * a. input=> sugar5kg, output=> {sugar, 5kg}
     * b. input=> 100ml =>{100ml}
     *
     * @param compoundWord
     * @return listOfWordTokens
     */
    public static List<String> numberTokenize(String compoundWord) {
        if (compoundWord == null) return new ArrayList<>();
        return new NumberTokenizer(compoundWord).getTokens();
    }
    
    /**
     * Splits a given word into the set of words considering number and
     * text parts are the delimiters themselves.
     *
     * @param compoundWord
     * @return listOfWordTokens
     */
    public static List<String> numericWordExpansion(String compoundWord) {
        if (compoundWord == null) return new ArrayList<>();
        return new WordExpansionTokenizer(compoundWord).getTokens();
    }
    
    /**
     * Splits a given compound word into its unique set of words specified in
     * the dictionary.
     *
     * @param compoundWord
     * @return listOfWordTokens
     */
    public static List<String> compoundWordExpansion(String compoundWord) {
        if (compoundWord == null) return new ArrayList<>();
        return new CompoundWordTokenizer(compoundWord).getTokens();
    }
    
}
