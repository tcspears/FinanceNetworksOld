/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 */

package financenetworks;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.apache.commons.io.filefilter.RegexFileFilter;

/**
 *
 * @author Taylor Spears (University of Edinburgh)
 */
public class FinanceNetworks {

    /**
     * @param args the command line arguments
     */
    

    public static void main(String[] args) throws Exception 
    {
       
        StanfordClassifier classifier = new StanfordClassifier();
        TextReader reader = new TextReader(classifier);
        
        File dir = new File("/Users/taylor/Downloads/output");
        FileFilter fileFilter = new RegexFileFilter("file_[0-9][0-9]*\\.xml");
        File[] files = dir.listFiles(fileFilter);

        ArrayList<LinkedHashSet<MoveEntry>> fileData = new ArrayList<>();
        
        for(File file : files)
        {
            LinkedHashSet fileOutput = reader.classifyTextFile(file.getAbsolutePath());
            EntryCleaner.cleanEntries(fileOutput);
            fileData.add(fileOutput);
        }
        
        for(LinkedHashSet dataSet : fileData)
        {
            
            for (Iterator it = dataSet.iterator(); it.hasNext();) {
                MoveEntry entry = (MoveEntry) it.next();
                
                System.out.println("=========================================================================");
                System.out.println("DATE: " + entry.getDate().toString());
                System.out.println("");
                System.out.println("ENTRY TEXT:");
                System.out.println(entry.getEntryText());
                System.out.println("");
                System.out.println("PEOPLE:");
                
                for(Person person : entry.people){
                    System.out.println(person.name + " (\"" + person.leftNeighborhood + "\", \"" + person.rightNeighborhood + "\")");
                }
                
                System.out.println("");
                
                System.out.println("ORGANIZATIONS:");
                
                for(Organization org : entry.organizations){
                    System.out.println(org.name + " (\"" + org.leftNeighborhood + "\", \"" + org.rightNeighborhood + "\")");
                }
                
                System.out.println("");
                
                System.out.println("LOCATIONS:");
                
                for(Location loc : entry.locations){
                    System.out.println(loc.name + " (\"" + loc.leftNeighborhood + "\", \"" + loc.rightNeighborhood + "\")");
                }
                
                System.out.println("");
            }
            
            
        }


    }
    
}
