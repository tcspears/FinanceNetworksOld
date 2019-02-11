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
        //HashSet<Person> matching = new HashSet<>(people);
        //matching.retainAll(entry.people);
        //return matching.size() > 0;
                
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
        //HashSet<Organization> matching = new HashSet<>(organizations);
        //matching.retainAll(entry.organizations);
        //return matching.size() > 0;
        
        // we must check if this's values contains entry's values (and not other
        // way around because full names are always used first in entries).
        
        
        for(Organization inputOrg : entry.organizations){
            
            for(Organization org : organizations){
                
                if(org.name.toLowerCase().contains(inputOrg.name.toLowerCase())){
                    return true;
                }
            }
        }
        
        return false;
        
    }
    
    public boolean matchingLocationsExist(MoveEntry entry)
    {
        //HashSet<Location> matching = new HashSet<>(locations);
        //matching.retainAll(entry.locations);
        //return matching.size() > 0;
        
        // we must check if this's values contains entry's values (and not other
        // way around because full names are always used first in entries).
        
        for(Location inputLoc : entry.locations){
            
            for(Location loc : locations){
                
                if(loc.name.toLowerCase().contains(inputLoc.name.toLowerCase())){
                    return true;
                }
            }
        }
        
        return false;
        
    }
    
    public void addPerson (String personName)
    {
        people.add(new Person(personName));
    }
    
    public void addOrganization (String orgName)
    {
        organizations.add(new Organization(orgName));
    }
    
    public void addLocation (String locationName)
    {
        locations.add(new Location(locationName));
    }
    
    public void mergeWithEntry (MoveEntry entry) throws Exception
    {
        if(!date.equals(entry.getDate()))
        {
            throw new Exception();
        }
        
        this.entryText = this.entryText + entry.getEntryText();
        
        //HashSet<Person> newPeople = new HashSet<>(people);
        //newPeople.addAll(entry.people);
        people.addAll(entry.people);
        
        //HashSet<Organization> newOrganizations = new HashSet<>(organizations);
        //newOrganizations.addAll(entry.organizations);
        organizations.addAll(entry.organizations);
        
        //HashSet<Location> newLocations = new HashSet<>(locations);
        //newLocations.addAll(entry.locations);
        locations.addAll(entry.locations);
        
        //return new MoveEntry(newEntryText, date, newPeople, newOrganizations, newLocations);
    }
    
    public HashSet<? extends Entity> getAllEntities()
    {
        HashSet<Entity> allEntities = new HashSet<>(people);
        allEntities.addAll(organizations);
        allEntities.addAll(locations);
        return allEntities;
    } 
    
}
