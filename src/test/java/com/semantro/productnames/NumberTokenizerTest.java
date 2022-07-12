package com.semantro.productnames;

import com.semantro.productnames.NumberTokenizer;

/**
 * This class is a part of the package semantro.com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-09.
 */
public class NumberTokenizerTest {
    
    public static void main(String[] args) {
        final String compoundWord = "sugar5kg";
        System.out.println("Tokens : " + new NumberTokenizer(compoundWord).getTokens());
        System.out.println("Empty Tokens : " + new NumberTokenizer("").getTokens());
        System.out.println("Start with number : " + new NumberTokenizer("500ml").getTokens());
        System.out.println("Start with number and compound : " + new NumberTokenizer("500ml50kg").getTokens());
    }
}
