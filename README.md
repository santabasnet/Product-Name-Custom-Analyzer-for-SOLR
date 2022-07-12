# Product-Name-Custom-Analyzer-for-SOLR

Various e-commerce sites requires synonym based query analysis with custom tokenizer. This work illustrates the implementation of such analyzer that can be use in Lucene/SOLR during indexing of various product names.

- Product Name Analysis:
  ```Scala
        String productWord = "babysoaps75ml";
        String result = new ProductStemmer().stem(productWord);
        System.out.println("Result: " + result);
        /**
        * Result: baby abys byso ysoa soap oaps babys abyso bysoa ysoap soaps babysoaps 75 ml 75ml
        **/
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
