/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financenetworks;

import java.time.LocalDate;
import java.util.HashSet;

/**
 *
 * @author taylor
 */
public class MoveEntry {
    
    private final String entryText;
    protected HashSet<String> people;
    protected HashSet<String> organizations;
    protected HashSet<String> locations;
    private LocalDate date;
    
    public MoveEntry (String entryText, LocalDate date)
    {
        this.entryText = entryText;
        this.date = date;
        this.people = new HashSet<String>();
        this.organizations = new HashSet<String>();
        this.locations = new HashSet<String>();
    }
    
    public MoveEntry (String entryText, LocalDate date, HashSet<String> people, HashSet<String> organizations, HashSet<String> locations)
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
        HashSet<String> matching = new HashSet<>(people);
        return matching.retainAll(entry.people);
    }
    
    public boolean matchingOrganizationsExist(MoveEntry entry)
    {
        HashSet<String> matching = new HashSet<>(organizations);
        return matching.retainAll(entry.organizations);
    }
    
    public boolean matchingLocationsExist(MoveEntry entry)
    {
        HashSet<String> matching = new HashSet<>(locations);
        return matching.retainAll(entry.locations);
    }
    
    public void addPerson (String personName)
    {
        people.add(personName);
    }
    
    public void addOrganization (String orgName)
    {
        organizations.add(orgName);
    }
    
    public void addLocation (String location)
    {
        locations.add(location);
    }
    
    public MoveEntry mergeWithEntry (MoveEntry entry) throws Exception
    {
        if(!date.equals(entry.getDate())){
            throw new Exception();
        }
        
        String newEntryText = this.entryText + entry.getEntryText();
        
        HashSet<String> newPeople = new HashSet<>(people);
        newPeople.addAll(entry.people);
        
        HashSet<String> newOrganizations = new HashSet<>(organizations);
        newOrganizations.addAll(entry.organizations);
        
        HashSet<String> newLocations = new HashSet<>(locations);
        newLocations.addAll(entry.locations);
        
        return new MoveEntry(newEntryText, date, newPeople, newOrganizations, newLocations);
    }
    
}
