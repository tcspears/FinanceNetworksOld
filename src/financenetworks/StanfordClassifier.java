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
        
        String leftNeighborhood;
        String rightNeighborhood;
        String entityName;
        
        for (Object triple : classified) 
        {
            Triple trip = (Triple) triple;
            
            entityName = inputText.substring((Integer) trip.second, (Integer) trip.third);
            leftNeighborhood = buildLeftNeighborhood(inputText, (int) trip.second);
            rightNeighborhood = buildRightNeighborhood(inputText, (int) trip.third);
           
            switch((String) trip.first)
            {
                case "PERSON": 
                    
                    Person person = new Person(entityName, leftNeighborhood, rightNeighborhood);
                    entry.addPerson(person);
                    break;
                
                case "ORGANIZATION":
                    
                    Organization org = new Organization(entityName, leftNeighborhood, rightNeighborhood);
                    entry.addOrganization(org);
                    break;
                    
                case "LOCATION":
                    
                    Location location = new Location(entityName, leftNeighborhood, rightNeighborhood);
                    entry.addLocation(location);
                    break;
            }
            
        }
        
        return entry;
    }
    
    
    
}
