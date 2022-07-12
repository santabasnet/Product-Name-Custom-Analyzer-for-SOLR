package com.semantro.productanalyzer;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.KeywordAttribute;

import java.io.IOException;

/**
 * This class is a part of the package com.semantro.productanalyzer and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-09.
 */
public class ProductStemFilter extends TokenFilter {
    
    /**
     * Product stemmer, custom implementation.
     */
    private ProductStemmer productStemmer = new ProductStemmer();
    
    /**
     * Term attributes.
     */
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    
    /**
     * Keyword attributes.
     */
    private final KeywordAttribute keywordAttr = addAttribute(KeywordAttribute.class);
    
    /**
     * Default constructor.
     */
    public ProductStemFilter(final TokenStream tokenStream) {
        super(tokenStream);
    }
    
    /**
     * Tokenization strategy.
     *
     * @return true if the token is valid type.
     * @throws IOException
     */
    @Override
    public final boolean incrementToken() throws IOException {
        if (input.incrementToken()) {
            final String term = termAtt.toString();
            if (!keywordAttr.isKeyword()) {
                final String str = productStemmer.stem(term);
                if ((str != null) && !str.equals(term)) {
                    termAtt.setEmpty().append(str);
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Set a alternative/custom {@link ProductStemmer} for this filter.
     *
     * @param productStemmer
     */
    public void setProductStemmer(final ProductStemmer productStemmer) {
        if (productStemmer != null) {
            this.productStemmer = productStemmer;
        }
    }
}
