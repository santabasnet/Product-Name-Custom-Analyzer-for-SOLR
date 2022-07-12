package com.semantro.productnames;

/**
 * This class is a part of the package com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-09.
 */
public class ProductDictionaryTest {
    public static void main(String[] args) {
        //System.out.println("Total Words : " + ProductDictionary.getTotalWords());
        //System.out.println("Word present[true] : " + ProductDictionary.isPresent("usena"));
        ProductDictionary.getWordNames().forEach(System.out::println);
    }
}
