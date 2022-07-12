package com.semantro.productnames;

import com.semantro.productnames.WordExpansionTokenizer;

/**
 * This class is a part of the package semantro.com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-09.
 */
public class WordExpansionTokenizerTest {
    public static void main(String[] args) {
        System.out.println("Tokens : " + new WordExpansionTokenizer("75ml").getTokens());
        System.out.println("Tokens : " + new WordExpansionTokenizer("75").getTokens());
        System.out.println("Tokens : " + new WordExpansionTokenizer("ml75").getTokens());
    }
}
