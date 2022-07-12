# Product-Name-Custom-Analyzer-for-SOLR

Various e-commerce sites requires synonym based query analysis with custom tokenizer. This work illustrates the implementation of such analyzer that can be use in Lucene/SOLR during indexing of various product names.

- Product Name Analysis:
  ```Scala        
        /** Example 1 **/
        String productWord = "babysoaps75ml";
        String result = new ProductStemmer().stem(productWord);
        System.out.println("Result: " + result);
        /**
        * Result: baby abys byso ysoa soap oaps babys abyso bysoa ysoap soaps babysoaps 75 ml 75ml
        * */
        
        /** Example 2 **/
        String productName = "पाउरोटी5kg";
        List<String> analyzedProduct = new PerformAnalysis().callAnalyzer(productName);
        System.out.println("Input: " + productName);
        System.out.println("Output: " + analyzedProduct);
        /**
        * Input: पाउरोटी5kg
        * Output: [पाउर, ाउरो, उरोट, रोटी, पाउरो, ाउरोट, उरोटी, पाउरोटी, 5, kg, 5kg]
        * */
  ```

- Compound Word Analysis:
  ```Scala
        String givenWord = "babysoaps";
        List<String> result = new CompoundWordTokenizer(givenWord).getWords();
        System.out.println(givenWord + " => " + result);
        
        String word1 = "babysoap";
        List<String> r1 = new CompoundWordTokenizer(word1).getWords();
        System.out.println(word1 + " => " + r1);
        /**
        * babysoaps => [babysoaps, baby, soap]
        * babysoap => [baby, soap, babysoap]
        **/
  ```
- Number Constituents Analysis:
  ```Scala
        final String compoundWord = "sugar5kg";
        System.out.println("Tokens : " + new NumberTokenizer(compoundWord).getTokens());
        System.out.println("Empty Tokens : " + new NumberTokenizer("").getTokens());
        System.out.println("Start with number : " + new NumberTokenizer("500ml").getTokens());
        System.out.println("Start with number and compound : " + new NumberTokenizer("500ml50kg").getTokens());
        /**
        * Tokens : [sugar, 5kg]
        * Empty Tokens : []
        * Start with number : [500ml]
        * Start with number and compound : [500ml, 50kg]
        * */
  ```
- Word Expansion Tokenizer:
  ```Scala
        System.out.println("Tokens : " + new WordExpansionTokenizer("75ml").getTokens());
        System.out.println("Tokens : " + new WordExpansionTokenizer("75").getTokens());
        System.out.println("Tokens : " + new WordExpansionTokenizer("ml75").getTokens());
        /**
        * Tokens : [75, ml, 75ml]
        * Tokens : [75]
        * Tokens : [ml, 75, ml75]
        * */
  ```
- EdgeNGrams Analysis:
  ```Scala
        String givenWord = "sugar5kg";
        List<String> result = new EdgeNGrams(givenWord).tokenize();
        System.out.println(result);
        /**
        * [suga, ugar, sugar]
        * */
  ```
