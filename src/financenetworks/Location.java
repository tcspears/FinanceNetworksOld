/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 */

package financenetworks;

/**
 *
 * @author Taylor Spears (University of Edinburgh)
 */
public class Location extends Entity 
{
    public Location(String name) 
    {
        super(name);
    }   
    
    public Location (String name, String leftNeighborhood, String rightNeighborhood)
    {
        super(name, leftNeighborhood, rightNeighborhood);
    }

    @Override
    public String getEntityType() 
    {
        return "LOCATION";
    }
}
