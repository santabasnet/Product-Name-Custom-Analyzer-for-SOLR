package com.semantro.productnames;

import java.util.List;

/**
 * This class is a part of the package com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-13.
 */
public class EdgeNGramsTest {
    
    public static void main(String[] args) {
        String givenWord = "sugar5kg";
        List<String> result = new EdgeNGrams(givenWord).tokenize();
        System.out.println(result);
    }
    
}
