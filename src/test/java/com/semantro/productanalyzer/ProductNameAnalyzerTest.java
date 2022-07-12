package com.semantro.productanalyzer;

import junit.framework.TestCase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
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
public class ProductNameAnalyzerTest extends TestCase {
    /**
     * Set up the test environment.
     */
    @Override
    protected void setUp() throws Exception {
    }
    
    @Test
    public void testProduct1() {
        String productName = "Johnson & Johnson Baby Milk+ Rice Cream, 100gm";
        List<String> expectedAnalyzedProduct = Arrays.asList("john", "ohns", "hnso", "nson", "johns", "ohnso", "hnson", "johnson", "john", "ohns", "hnso", "nson", "johns", "ohnso", "hnson", "johnson", "baby", "milk", "rice", "crea", "ream", "cream", "100", "gm", "100gm");
        List<String> programAnalyzedProduct = new PerformAnalysis().callAnalyzer(productName);
        //System.out.println(programAnalyzedProduct);
        Assert.assertArrayEquals(expectedAnalyzedProduct.toArray(), programAnalyzedProduct.toArray());
    }
    
    @Test
    public void testProduct2() {
        String productName = "babysoaps75ml";
        List<String> expectedAnalyzedProduct = Arrays.asList("baby", "abys", "byso", "ysoa", "soap", "oaps", "babys", "abyso", "bysoa", "ysoap", "soaps", "babysoaps", "75", "ml", "75ml");
        List<String> programAnalyzedProduct = new PerformAnalysis().callAnalyzer(productName);
        //System.out.println(programAnalyzedProduct);
        Assert.assertArrayEquals(expectedAnalyzedProduct.toArray(), programAnalyzedProduct.toArray());
    }
    
}

class PerformAnalysis {
    /**
     * Perform the product name analysis.
     *
     * @param productName
     * @return analyzerOutput in the form of list of String.
     */
    public List<String> callAnalyzer(String productName) {
        final ProductNameAnalyzer productAnalyzer = new ProductNameAnalyzer();
        List<String> result = new ArrayList<>();
        try {
            final TokenStream stream = productAnalyzer.tokenStream(null, new StringReader(productName));
            stream.reset();
            while (stream.incrementToken()) {
                result.add(stream.getAttribute(CharTermAttribute.class).toString());
            }
            return result;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}

