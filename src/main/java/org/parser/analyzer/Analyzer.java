package org.parser.analyzer;

import java.util.HashMap;
import java.util.Map;

public class Analyzer {
    public Map<String, Integer> CountWords(String text){
        String[] words = text.split("[ ,.!?\";:\\[\\]()\n\r\t]");

        Map<String, Integer> countedWords = new HashMap<>();

        for (String word : words){
            if (word.equals("")) continue;
            String generalizedWord = word.toLowerCase();
            Integer key = countedWords.get(generalizedWord);
            if(key ==null){
                 key = 0;
            }
            countedWords.put(generalizedWord, key+1);
        }
        return countedWords;
    }

}
