/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 */

package financenetworks;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 *
 * @author Taylor C. Spears (University of Edinburgh)
 */
public class EntryCleaner {
    
    private static boolean beginsWithPronoun (MoveEntry entry)
    {
        String entryText = entry.getEntryText();
        String firstWord = entryText.substring(0, entryText.indexOf(' ')).toLowerCase();
        
        return firstWord.equals("she") || firstWord.equals("he");
    }
    
    private static boolean haveEntitiesInCommon (MoveEntry firstEntry, MoveEntry secondEntry)
    {
        boolean matchingPeople = firstEntry.matchingPeopleExist(secondEntry);
        boolean matchingOrgs = firstEntry.matchingOrganizationsExist(secondEntry);
        
        return matchingPeople || matchingOrgs;
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
            boolean shouldCombineWithPrev = commonEntities || pronoun;
            
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
