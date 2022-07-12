package com.semantro.productnames;

import java.util.List;

/**
 * This class is a part of the package com.semantro.productanalyzer and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-09.
 */
public class CompoundWordTokenizerTest {
    
    public static void main(String[] args) {
        String givenWord = "babysoaps";
        List<String> result = new CompoundWordTokenizer(givenWord).getWords();
        System.out.println(givenWord + " => " + result);
        
        String word1 = "babysoap";
        List<String> r1 = new CompoundWordTokenizer(word1).getWords();
        System.out.println(word1 + " => " + r1);
    }
}
