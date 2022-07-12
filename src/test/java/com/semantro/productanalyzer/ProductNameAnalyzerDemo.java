package com.semantro.productanalyzer;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a part of the package com.semantro.productanalyzer and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-10.
 */
public class ProductNameAnalyzerDemo {
    
    /**
     * Demo main here.
     *
     * @param args
     */
    public static void main(String[] args) {
        //String productName = "Johnson & Johnson Baby Eggs BabyiceSoaps Milk+ Rice mood मसल, मस्सल Cream, 100gm";
        String productName = "पाउरोटी5kg";
        List<String> analyzedProduct = new PerformAnalysis().callAnalyzer(productName);
        System.out.println("Input: " + productName);
        System.out.println("Output: " + analyzedProduct);
    }
    
    
}
