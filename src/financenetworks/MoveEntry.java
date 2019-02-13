/*
 * Copyright 2019 Taylor C. Spears (University of Edinburgh).
 */

package financenetworks;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.TreeMap;

/**
 *
 * @author Taylor Spears (University of Edinburgh)
 */
public class MoveEntry {
    
    private String entryText;
    protected HashSet<Person> people;
    protected HashSet<Organization> organizations;
    protected HashSet<Location> locations;
    private LocalDate date;
    
    public MoveEntry (String entryText, LocalDate date)
    {
        this.entryText = entryText;
        this.date = date;
        this.people = new HashSet<>();
        this.organizations = new HashSet<>();
        this.locations = new HashSet<>();
    }
    
    public MoveEntry (String entryText, LocalDate date, HashSet<Person> people, HashSet<Organization> organizations, HashSet<Location> locations)
    {
        this.entryText = entryText;
        this.date = date;
        this.people = people;
        this.organizations = organizations;
        this.locations = locations;
    }
    
    public String getEntryText()
    {
        return entryText;
    }
    
    public LocalDate getDate()
    {
        return date;
    }
    
    public boolean checkPersonExists(String personName)
    {
        return people.contains(personName);
    }
    
    public boolean checkOrganizationExists (String orgName)
    {
        return organizations.contains(orgName);
    }
    
    public boolean checkLocationExists (String locationName)
    {
        return locations.contains(locationName);
    }
    
    public boolean matchingPeopleExist(MoveEntry entry)
    {
        // we must check if this's values contains entry's values (and not other
        // way around because full names are always used first in entries).
        
        for(Person inputPerson : entry.people)
        {
            for(Person person : people)
            {
                if(person.name.toLowerCase().contains(inputPerson.name.toLowerCase())){
                    return true;
                }

            }
            
        }
        
        return false;
    }
    
    public boolean matchingOrganizationsExist(MoveEntry entry)
    {   
        // we must check if this's values contains entry's values (and not other
        // way around because full names are always used first in entries).
        
        for(Organization inputOrg : entry.organizations)
        {
            
            for(Organization org : organizations)
            {
                
                if(org.name.toLowerCase().contains(inputOrg.name.toLowerCase()))
                {
                    return true;
                }
            }
        }
        
        return false;
        
    }
    
    public boolean matchingLocationsExist(MoveEntry entry)
    {
        // we must check if this's values contains entry's values (and not other
        // way around because full names are always used first in entries).
        
        for(Location inputLoc : entry.locations)
        {
            
            for(Location loc : locations)
            {
                
                if(loc.name.toLowerCase().contains(inputLoc.name.toLowerCase()))
                {
                    return true;
                }
            }
        }
        
        return false;
        
    }
    
    public void addPerson (Person person)
    {
        people.add(person);
    }
    
    public void addOrganization (Organization org)
    {
        organizations.add(org);
    }
    
    public void addLocation (Location location)
    {
        locations.add(location);
    }
    
    public void mergeWithEntry (MoveEntry entry) throws Exception
    {
        if(!date.equals(entry.getDate()))
        {
            throw new Exception();
        }
        
        this.entryText = this.entryText + entry.getEntryText();
        
        people.addAll(entry.people);
        organizations.addAll(entry.organizations);
        locations.addAll(entry.locations);
        
        // todo: add functionality to update position number of entities, and 
        // re-define the left/right neighborhoods upon merging. 
        
    }
    
    public HashSet<? extends Entity> getAllEntities()
    {
        HashSet<Entity> allEntities = new HashSet<>(people);
        allEntities.addAll(organizations);
        allEntities.addAll(locations);
        return allEntities;
    } 
    
}
