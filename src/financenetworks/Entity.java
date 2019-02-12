/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 */

package financenetworks;

import java.util.Objects;

/**
 *
 * @author Taylor Spears (University of Edinburgh)
 */
public abstract class Entity 
{
    public final String name;
    public final String leftNeighborhood;
    public final String rightNeighborhood;
    
    public Entity (String name)
    {
        this.name = name;
        this.leftNeighborhood = new String();
        this.rightNeighborhood = new String();
    }
    
    public Entity (String name, String leftNeighborhood, String rightNeighborhood)
    {
        this.name = name;
        this.leftNeighborhood = leftNeighborhood;
        this.rightNeighborhood = rightNeighborhood;
    }
    
    public boolean isInLeftNeighborhood (String input)
    {
        return leftNeighborhood.contains(input);
    }
    
    public boolean isInRightNeighborhood (String input)
    {
        return rightNeighborhood.contains(input);
    }
    
    // Need to think about how to define 'equality' in these cases.
    
    @Override
    public String toString()
    {
        return name + ":" + getEntityType();
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        }
        
        if(!(o instanceof Entity))
        {
            return false;
        }
        
        Entity c = (Entity) o;
        
        return this.name.equals(c.name);
        
    }

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
    public abstract String getEntityType();
}
