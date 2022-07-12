package com.semantro.productnames;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.synonym.SolrSynonymParser;
import org.apache.lucene.analysis.synonym.SynonymMap;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

/**
 * This class is a part of the package com.semantro.productnames and the package
 * is a part of the project productsnameanalyzer.
 * <p>
 * Integrated ICT Pvt. Ltd. Jwagal, Lalitpur, Nepal.
 * https://www.integratedict.com.np
 * <p>
 * Created by Santa on 2019-05-11.
 */
public class ProductSynonyms {
    /**
     * Synonym file.
     */
    private static final String synonymFile = "productsynonyms2.dat";
    
    /**
     * Build the synonym map here.
     */
    private static final SynonymMap synonymMap =
            new ProductNameStore(synonymFile).buildSynonymMap();
    
    /**
     * Returns the constructed synonyms build from the file.
     */
    public static SynonymMap getMap() {
        return synonymMap;
    }
}

class ProductNameStore {
    
    /**
     * Dictionary file.
     */
    
    private final String synonymFile;
    
    /**
     * Default constructor.
     * Load all the dictionary words from the file.
     *
     * @param synonymFile
     */
    public ProductNameStore(String synonymFile) {
        this.synonymFile = synonymFile;
    }
    
    /**
     * Read dictionary file from here.
     *
     * @return finalSynonymMap
     */
    public SynonymMap buildSynonymMap() {
        try {
            final SolrSynonymParser synonymParser = new SolrSynonymParser(true, true, new WhitespaceAnalyzer());
            synonymParser.parse(getSynonymReader());
            return synonymParser.build();
        } catch (Exception ee) {
            return getEmptySynonymMap();
        }
    }
    
    /**
     * Build empty synonym map.
     *
     * @return emptySynonymMap
     */
    private SynonymMap getEmptySynonymMap() {
        try {
            return new SynonymMap.Builder(true).build();
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Get file reader from the synonym file name.
     */
    private Reader getSynonymReader() {
        try {
            InputStream inputStream = ProductNameStore.class.getClassLoader().getResourceAsStream(synonymFile);
            return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            return new StringReader("");
        }
    }
}

