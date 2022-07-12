package com.semantro.productanalyzer;

/**
 * This class is a part of the package com.semantro.productanalyzer and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-10.
 */
public class ProductStemmerTest {
    /**
     * Main.
     */
    public static void main(String[] args){
        String productWord = "babysoaps75ml";
        String result = new ProductStemmer().stem(productWord);
        System.out.println("Result: " + result);
    }
}
