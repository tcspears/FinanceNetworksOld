/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
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
