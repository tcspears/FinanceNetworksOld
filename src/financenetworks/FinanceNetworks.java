/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financenetworks;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sequences.DocumentReaderAndWriter;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.Triple;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author taylor
 */
public class FinanceNetworks {

    /**
     * @param args the command line arguments
     */
    

    public static void main(String[] args) throws Exception 
    {
        
        
        MoveEntry test = new MoveEntry("This is a test", LocalDate.parse("2016-01-01"));
        test.addPerson("John Smith");
        test.addPerson("Frank Bull");
        
        MoveEntry test1 = new MoveEntry("This is a test", LocalDate.parse("2016-01-01"));
        test1.addPerson("John Smith");
        
        System.out.println(test.matchingPeople(test1));
        
        
       /**
        
        String serializedClassifier = "classifiers/english.all.3class.distsim.crf.ser.gz";
        AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);
        
        String inputFile = "/Users/taylor/Downloads/output/file_2.txt";
        BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
        
        LinkedHashMap<String, List> fileData = new LinkedHashMap<>();
        
        String line = inputReader.readLine();
        
        while(line != null)
        {
            fileData.put(line, classifier.classifyToCharacterOffsets(line));
            line = inputReader.readLine();
        }
        
        System.out.println("Classification done");
        
        for(String key : fileData.keySet())
        {
            System.out.println(key + "|");
            List data = fileData.get(key);
            
            if(!data.isEmpty())
            {
                for (Object triple : data) 
                {
                    Triple trip = (Triple) triple;
                    System.out.println("Type:" + trip.first + "|" + key.substring((Integer) trip.second, (Integer) trip.third));
                }
                
            }
        }
        * 
        * 
        
        */

    }
    
}
