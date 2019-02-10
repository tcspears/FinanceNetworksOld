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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashSet;

/**
 *
 * @author Taylor Spears (University of Edinburgh)
 */
public class TextReader {
    
    protected final TextClassifier classifier;
    
    public TextReader (TextClassifier classifier)
    {
        this.classifier = classifier;
    }
    
    public LinkedHashSet classifyTextFile (String fileLocation) throws FileNotFoundException, IOException
    {
        
        BufferedReader inputReader = new BufferedReader(new FileReader(fileLocation));
        
        LinkedHashSet<MoveEntry> fileData = new LinkedHashSet<>();
        
        String line = inputReader.readLine();
        
        while(line != null)
        {
            
            // need to add functionality to extract dates
            MoveEntry entry = classifier.classifyText(line, LocalDate.parse("2018-01-01"));
            
            int numEntities = entry.getAllEntities().size();
            
            if(numEntities > 0){
                fileData.add(entry);
            }
          
            line = inputReader.readLine();
        }
        
        return fileData;
        
    }
    
}
