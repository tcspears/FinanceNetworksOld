/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 */

package financenetworks;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Taylor Spears (University of Edinburgh)
 */
public class TextReader {
    
    protected final TextClassifier classifier;
    
    protected final DateTimeFormatter dateFormat;
    
    public TextReader (TextClassifier classifier)
    {
        this.classifier = classifier;
        
        dateFormat = new DateTimeFormatterBuilder()
        .appendPattern("MMM yyyy")
        .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
        .toFormatter();
    }
    
    public LocalDate dateFromMonYear (String monYear)
    {        
        return LocalDate.parse(monYear, dateFormat);
    }
    
    public String extractTextSection (String fileContent)
    {
        // Regex to match <text> tags; save to fileText string
        final Pattern textPattern = Pattern.compile("<text>(.+?)</text>", Pattern.DOTALL);
        final Matcher textMatcher = textPattern.matcher(fileContent);
        textMatcher.find();
        
        return textMatcher.group(1);
    }
    
    public LocalDate extractDate (String fileContent)
    {
        // Regex to match docdate tags; save to dateText string and convert to a localDate
        final Pattern datePattern = Pattern.compile("<attr name=\"docdate\">(.+?)</attr>", Pattern.DOTALL);
        final Matcher dateMatcher = datePattern.matcher(fileContent);
        dateMatcher.find();
        
        String dateText = dateMatcher.group(1);
        return dateFromMonYear(dateText);
    }
    
    public LinkedHashSet classifyTextFile (String fileLocation) throws FileNotFoundException, IOException
    {
        
        // Read file contents into String
        String fileContent = new String (Files.readAllBytes(Paths.get(fileLocation)));
        String fileText = extractTextSection(fileContent);
        fileText = StringUtils.stripAccents(fileText);
        LocalDate date = extractDate(fileContent);
        
        BufferedReader inputReader = new BufferedReader(new StringReader(fileText));
        
        LinkedHashSet<MoveEntry> fileData = new LinkedHashSet<>();
        
        String line = inputReader.readLine();
        
        while(line != null)
        {
            
            // need to add functionality to extract dates
            MoveEntry entry = classifier.classifyText(line, date);
            
            int numEntities = entry.getAllEntities().size();
            
            if(numEntities > 0){
                fileData.add(entry);
            }
          
            line = inputReader.readLine();
        }
        
        return fileData;
        
    }
    
}
