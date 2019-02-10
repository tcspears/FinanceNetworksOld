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

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Taylor Spears (University of Edinburgh)
 */
public class StanfordClassifier extends TextClassifier {
    
    protected AbstractSequenceClassifier<CoreLabel> classifier;
    
    public StanfordClassifier () throws Exception
    {
        this("classifiers/english.all.3class.distsim.crf.ser.gz");
    }
    
    public StanfordClassifier (String classifierLocation) throws Exception
    {
        classifier = CRFClassifier.getClassifier(classifierLocation);
    }

    @Override
    public MoveEntry classifyText (String inputText, LocalDate textDate) 
    {
        List classified = classifier.classifyToCharacterOffsets(inputText);
        
        MoveEntry entry = new MoveEntry(inputText, textDate);
        
        for (Object triple : classified) 
        {
            Triple trip = (Triple) triple;
           
            switch((String) trip.first)
            {
                case "PERSON": 
                    
                    entry.addPerson(inputText.substring((Integer) trip.second, (Integer) trip.third));
                    break;
                
                case "ORGANIZATION":
                    
                    entry.addOrganization(inputText.substring((Integer) trip.second, (Integer) trip.third));
                    break;
                    
                case "LOCATION":
                    
                    entry.addLocation(inputText.substring((Integer) trip.second, (Integer) trip.third));
                    break;
            }
            
        }
        
        return entry;
    }
    
    
    
}
