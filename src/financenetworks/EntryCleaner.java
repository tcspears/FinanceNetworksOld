/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 */

package financenetworks;

import static java.lang.Integer.min;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 *
 * @author Taylor C. Spears (University of Edinburgh)
 */
public class EntryCleaner {
    
    // entry begins with a pronoun
    
    private static boolean beginsWithPronoun (MoveEntry entry)
    {
        String entryText = entry.getEntryText();
        String firstWord = entryText.substring(0, entryText.indexOf(' ')).toLowerCase();
        
        return firstWord.equals("she") || firstWord.equals("he");
    }
    
    // first and second entry have entities in common (name/org only)
    
    private static boolean haveEntitiesInCommon (MoveEntry firstEntry, MoveEntry secondEntry)
    {
        boolean matchingPeople = firstEntry.matchingPeopleExist(secondEntry);
        boolean matchingOrgs = firstEntry.matchingOrganizationsExist(secondEntry);
        
        return matchingPeople || matchingOrgs;
    }
    
    // Check to see if the end of the first entry includes a period, and
    // if the start of the second entry includes an uppercase letter. If both
    // are not true, then the entries should be combined.
    
    private static boolean endStartPunctuationCheck (MoveEntry firstEntry, MoveEntry secondEntry)
    {
        int lastCharacterPosition = firstEntry.getEntryText().length() - 1;
        boolean doesNotEndWithPeriod = !firstEntry.getEntryText().substring(lastCharacterPosition).equals('.');
        
        String firstCharacterAsCapital = secondEntry.getEntryText().substring(0, 0).toUpperCase();
        boolean doesNotStartWithCapital = !secondEntry.getEntryText().substring(0, 0).equals(firstCharacterAsCapital);
        
        return doesNotEndWithPeriod && doesNotStartWithCapital;
    }
    
    // If the entry contains the word 'joins' in the first five words of the entryText, it probably needs to be 
    // combined with the previous entry.
    
    private static boolean startsWithJoins (MoveEntry entry)
    {
        String[] entryTextSplit = entry.getEntryText().split("\\s+");
        
        int subStringLength = min(entryTextSplit.length, 5);
        
        for(int i = 0; i < subStringLength; i++)
        {
            if(entryTextSplit[i].equals("joins")){
                return true;
            }
        }
        
        return false;
    }
    
    
    public static void cleanEntries (LinkedHashSet<MoveEntry> entries) throws Exception
    {        
        
        Iterator<MoveEntry> iter = entries.iterator();
        
        // Skip first entry and save as previous
        MoveEntry previous = iter.next();

        while(iter.hasNext())
        {
            MoveEntry current = iter.next();
            boolean commonEntities = haveEntitiesInCommon(previous, current);
            boolean pronoun = beginsWithPronoun(current);
            boolean punctuation = endStartPunctuationCheck(previous, current);
            boolean joins = startsWithJoins(current);
            boolean shouldCombineWithPrev = commonEntities || pronoun || punctuation || joins;
            
            if(shouldCombineWithPrev)
            {
                // Merge previous with current and remove current from entries
                previous.mergeWithEntry(current);
                iter.remove();
            } 
            
            else 
            {
                // If nothing was removed, then advance previous pointer to current
                previous = current;
            }
           
        }
                
    }
    
    
}
