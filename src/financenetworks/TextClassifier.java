/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 */

package financenetworks;

import java.time.LocalDate;

/**
 *
 * @author Taylor Spears (University of Edinburgh)
 */
public abstract class TextClassifier {
    
    public abstract MoveEntry classifyText(String inputText, LocalDate textDate);
    
    // Build substring capturing the 3 words immediately preceding an entity
    public String buildLeftNeighborhood (String input, int entityStartPosition)
    {
        int subsetSize = Integer.max(0, entityStartPosition - 1);
        String subset = input.substring(0, subsetSize);
        String[] words = subset.split("\\s+");
        
        StringBuilder leftNeighborhood = new StringBuilder();
        
        int wordsBegin = Integer.max(0, words.length - 3);
        
        for(int i = wordsBegin; i < words.length; i++)
        {
            leftNeighborhood.append(words[i]);
            leftNeighborhood.append(" ");
        }
        
        return leftNeighborhood.toString();
    }
    
    // Build substring capturing the 3 words immediately following an entity.
    public String buildRightNeighborhood (String input, int entityEndPosition)
    {
        int subsetSize = Integer.min(input.length(), entityEndPosition + 1);
        String subset = input.substring(subsetSize);
        String[] words = subset.split("\\s+");
        
        StringBuilder rightNeighborhood = new StringBuilder();
        
        int wordsEnd = Integer.min(3, words.length);
        
        for(int i = 0; i < wordsEnd; i++)
        {
            rightNeighborhood.append(words[i]);
            rightNeighborhood.append(" ");
        }
        
        return rightNeighborhood.toString();
    }
}
