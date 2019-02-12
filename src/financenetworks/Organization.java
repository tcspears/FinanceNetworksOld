/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 */

package financenetworks;

/**
 *
 * @author Taylor Spears (University of Edinburgh)
 */
public class Organization extends Entity 
{
    public Organization(String name) 
    {
        super(name);
    }   
    
    public Organization (String name, String leftNeighborhood, String rightNeighborhood)
    {
        super(name, leftNeighborhood, rightNeighborhood);
    }

    @Override
    public String getEntityType() 
    {
        return "ORGANIZATION";
    }
}
