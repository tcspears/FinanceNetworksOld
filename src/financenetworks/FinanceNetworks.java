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
                    System.out.println(person.name);
                }
                
                System.out.println("");
                
                System.out.println("ORGANIZATIONS:");
                
                for(Organization org : entry.organizations){
                    System.out.println(org.name);
                }
                
                System.out.println("");
                
                System.out.println("LOCATIONS:");
                
                for(Location loc : entry.locations){
                    System.out.println(loc.name);
                }
                
                System.out.println("");
            }
            
            
        }


    }
    
}
