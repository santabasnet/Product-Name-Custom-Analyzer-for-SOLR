package com.semantro.productanalyzer;

import com.semantro.productnames.ProductSynonyms;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.core.DecimalDigitFilter;
import org.apache.lucene.analysis.miscellaneous.SetKeywordMarkerFilter;
import org.apache.lucene.analysis.miscellaneous.WordDelimiterFilter;
import org.apache.lucene.analysis.ngram.NGramTokenFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.synonym.SynonymFilter;
import org.apache.lucene.util.Version;

import java.util.Arrays;

/**
 * This class is a part of the package com.semantro.com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-09.
 */
public class ProductNameAnalyzer extends StopwordAnalyzerBase {
    
    /**
     * Definition of stem exclusion set.
     */
    private final CharArraySet stemExclusionSet;
    
    /**
     * Default stop word set, can be loaded either by set or
     * from file.
     *
     * @return stopWordsSet
     */
    public static CharArraySet getDefaultStopSet() {
        return DefaultSetHolder.DEFAULT_STOP_SET;
    }
    
    /**
     * Load stop word here.
     */
    private static class DefaultSetHolder {
        static final CharArraySet DEFAULT_STOP_SET
                = new CharArraySet(Arrays.asList("!", "$", "%", "^", "&", "*", "+", "/", "\\"), false);
    }
    
    /**
     * Overloaded constructor with given stop words as well as the stem exclusion set,
     * which are used during stemming, not to process further by stemmer.
     *
     * @param stopWords
     * @param stemExclusionSet
     */
    public ProductNameAnalyzer(CharArraySet stopWords, CharArraySet stemExclusionSet) {
        super(stopWords);
        this.stemExclusionSet = CharArraySet.unmodifiableSet(CharArraySet.copy(stemExclusionSet));
    }
    
    /**
     * Overloaded constructor with given stop words.
     *
     * @param stopWords
     */
    public ProductNameAnalyzer(CharArraySet stopWords) {
        this(stopWords, CharArraySet.EMPTY_SET);
    }
    
    /**
     * Default constructor.
     */
    public ProductNameAnalyzer() {
        this(ProductNameAnalyzer.DefaultSetHolder.DEFAULT_STOP_SET);
    }
    
    /**
     * This is the product name analyzer.
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        final Tokenizer source = new StandardTokenizer();
        TokenStream result = new LowerCaseFilter(source);
        
        if (getVersion().onOrAfter(Version.LUCENE_7_0_0)) {
            result = new DecimalDigitFilter(result);
        }
        
        if (!stemExclusionSet.isEmpty()) {
            result = new SetKeywordMarkerFilter(result, stemExclusionSet);
        }
        
        result = new ProductStemFilter(result);
        
        //noinspection deprecation
        result = new WordDelimiterFilter(result, WordDelimiterFilter.ALPHA, CharArraySet.EMPTY_SET);
        
        //noinspection deprecation
        result = new SynonymFilter(result, ProductSynonyms.getMap(), true);
        
        return new TokenStreamComponents(source, result);
    }
}
