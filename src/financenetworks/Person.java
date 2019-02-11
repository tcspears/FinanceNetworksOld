/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 */

package financenetworks;

/**
 *
 * @author Taylor Spears (University of Edinburgh)
 */
public class Person extends Entity
{    
    public Person(String name) 
    {
        super(name);
    }   

    @Override
    public String getEntityType() 
    {
        return "PERSON";
    }
}
