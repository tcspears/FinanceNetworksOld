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

import java.util.LinkedHashSet;

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

        String inputFile = "/Users/taylor/Downloads/output/file_2.txt";
        
        LinkedHashSet fileOutput = reader.classifyTextFile(inputFile);
        
        System.out.println("Classification done");


    }
    
}
