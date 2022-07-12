package com.semantro.productnames;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is a part of the package com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-09.
 */
public class ProductDictionary {
    
    /**
     * Load all the dictionary words from the file.
     */
    private static final Set<String> dictionaryWords = new ProductWords().read();
    
    /**
     * Returns the set of product words.
     *
     * @return setOfWords available in the product dictionary.
     */
    public static Set<String> getWordNames() {
        return dictionaryWords;
    }
    
    /**
     * Returns total number of words.
     *
     * @return totalWords present in the dictionary.
     */
    public static int getTotalWords() {
        return dictionaryWords.size();
    }
    
    /**
     * Checks weather the given word is present in the dictionary or not.
     *
     * @param word
     * @return true if the given word is present in the product dictionary.
     */
    public static boolean isPresent(String word) {
        return dictionaryWords.contains(word);
    }
}

class ProductWords {
    /**
     * Dictionary file.
     */
    private static final String dictionaryFile = "productwords2.dat";
    private final Set<String> dictionaryWords;
    
    /**
     * Load all the dictionary words from the file.
     */
    ProductWords() {
        this.dictionaryWords = loadDictionaryWords();
    }
    
    /**
     * Read dictionary file from here.
     */
    private Set<String> loadDictionaryWords() {
        Set<String> dictionary = new HashSet<>();
        try {
            InputStream inputStream = ProductDictionary.class.getClassLoader().getResourceAsStream(dictionaryFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            for (String line; (line = reader.readLine()) != null; )
                dictionary.add(line.trim());
            return dictionary;
        } catch (Exception ee) {
            return dictionary;
        }
    }
    
    /**
     * Returns the dictionary words.
     */
    Set<String> read() {
        return dictionaryWords;
    }
}
