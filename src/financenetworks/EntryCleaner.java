/*
 * The MIT License
 *
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
