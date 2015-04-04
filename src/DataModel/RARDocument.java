/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;
import java.util.ArrayList;
import java.util.Objects;
/**
 * An object of this class represents an entire RAR system, complete with 
 * a list of new and launched events, and a roster. This will be serialized
 * and deserialized and only one object of this class should exist during the 
 * run of the RAR system.
 * @author Steven
 */
public class RARDocument 
{
    
    /**
     * a set of all members in the system
     */
    private MemberSet roster;
    /**
     * A list of events to be launched
     */
    private ArrayList<Event> futureEvents;
    /**
     * A list of events that have been launched and are
     * reviewable
     */
    private ArrayList<Event> completedEvents;

    public RARDocument() 
    {
        roster = new MemberSet();
        futureEvents = new ArrayList<>();
        completedEvents = new ArrayList<>();
    }
    

    public boolean createEvent(String description)
    {
        if (!this.futureEvents.contains(new Event(description)))
        {
            return this.futureEvents.add(new Event(description));
        } else
        {
            System.err.println("Already an event with that description!");
            return false;
        }
    }
    
    public MemberSet getRoster() {
        return roster;
    }

    public void setRoster(MemberSet roster) {
        this.roster = roster;
    }

    public ArrayList<Event> getFutureEvents() {
        return futureEvents;
    }

    public void setFutureEvents(ArrayList<Event> futureEvents) {
        this.futureEvents = futureEvents;
    }

    public ArrayList<Event> getCompletedEvents() {
        return completedEvents;
    }

    public void setCompletedEvents(ArrayList<Event> completedEvents) {
        this.completedEvents = completedEvents;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.roster);
        hash = 59 * hash + Objects.hashCode(this.futureEvents);
        hash = 59 * hash + Objects.hashCode(this.completedEvents);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RARDocument other = (RARDocument) obj;
        if (!Objects.equals(this.roster, other.roster)) {
            return false;
        }
        if (!Objects.equals(this.futureEvents, other.futureEvents)) {
            return false;
        }
        if (!Objects.equals(this.completedEvents, other.completedEvents)) {
            return false;
        }
        return true;
    }
    
    
    
}
