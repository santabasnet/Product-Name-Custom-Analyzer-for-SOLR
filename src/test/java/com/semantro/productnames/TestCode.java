package com.semantro.productnames;

import java.util.Arrays;
import java.util.List;

/**
 * This class is a part of the package com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-09.
 */
public class TestCode {
    
    public static void main(String[] args){
        
        List<String> words = Arrays.asList("hello", "world", "nepal");
        String result = "";
        words.stream().map((tStr) -> tStr + " ").reduce(result, String::concat).trim();
        
        System.out.println(result);
        
        
    }
}
