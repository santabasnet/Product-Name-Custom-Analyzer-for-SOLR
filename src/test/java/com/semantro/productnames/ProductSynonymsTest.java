package com.semantro.productnames;

import org.apache.lucene.analysis.synonym.SynonymMap;

import java.util.Arrays;
import java.util.Map;

/**
 * This class is a part of the package com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-11.
 */
public class ProductSynonymsTest {
    
    /**
     * Main class.0
     */
    public static void main(String[] args){
        SynonymMap synonymMap = ProductSynonyms.getMap();
        System.out.println("Total Entries : " + synonymMap.words.size());
    }
}
