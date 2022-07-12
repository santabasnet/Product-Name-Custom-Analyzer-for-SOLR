package com.semantro.productanalyzer;

import com.semantro.productnames.CompoundWordTokenizer;
import com.semantro.productnames.EdgeNGrams;
import com.semantro.productnames.NumberTokenizer;
import com.semantro.productnames.WordExpansionTokenizer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class is a part of the package com.semantro.productanalyzer and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-09.
 */
public class ProductStemmer {
    
    /**
     * Default constructor.
     */
    public ProductStemmer() {
    }
    
    /**
     * Perform stemming, word expansion and so on.
     *
     * @param word
     * @return expandedWord
     */
    public String stem(String word) {
        return Stream
                .concat(getEdgeWords(word).stream(), getExpandedWords(word).stream())
                .distinct().collect(Collectors.joining(" "));
    }
    
    /**
     * Find all the edge tokens.
     *
     * @param word
     * @return edgeTokens.
     */
    private List<String> getEdgeWords(String word) {
        return new EdgeNGrams(word).tokenize();
    }
    
    /**
     * Find all the words expanded by series of tokenizer.
     *
     * @param word
     * @return listOfWordTokens
     */
    private List<String> getExpandedWords(String word) {
        return new NumberTokenizer(word)
                .getTokens().stream()
                .flatMap(tokenWord -> new CompoundWordTokenizer(tokenWord).getWords().stream())
                .flatMap(token -> new WordExpansionTokenizer(token).getTokens().stream())
                .collect(Collectors.toList());
    }
}
