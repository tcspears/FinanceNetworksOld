/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 */

package financenetworks;

import java.time.LocalDate;

/**
 *
 * @author Taylor Spears (University of Edinburgh)
 */
public abstract class TextClassifier {
    
    public abstract MoveEntry classifyText(String inputText, LocalDate textDate);
    
}
