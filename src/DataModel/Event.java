/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;
import java.util.Date;
import java.util.Objects;
/**
 * An event is launchable and contains a memberSet for invites and a memberSet for 
 * attendees. Since invites are removed as they attend, those in the invites list after they are closed
 * are non-attendees.
 * @author Steven
 */
public class Event 
{
    
    private MemberSet invites;
    private MemberSet attendees;
    private String Description;
    /**
     * This is when the event was created.
     */
    private Date created;
    /**
     * This is the date at which the event was closed by the user.
     */
    private Date closed;
    /**
     * this is when the event should start.
     */
    private Date timeOfEvent;

    public Event(String Description) 
    {
        this.Description = Description;
        created = new Date();
        timeOfEvent = null;
        attendees = new MemberSet();
    }

    public Event(MemberSet invites, String Description) 
    {
        this.invites = invites;
        this.Description = Description;
        created = new Date();
        attendees = new MemberSet();
    }
    
    public boolean addInvite(Member m)
    {
        if (invites == null)
        {
            invites = new MemberSet();
            return invites.addMember(m);
        } else
        {
            return invites.addMember(m);
        }
    }
    /**
     * Use this for making a member be recorded as attended.
     * @param m
     * @return if the member successfully is recorded as attended.
     */
    public boolean MemberAttended(Member m)
    {
        if (invites != null)
        {
            if (invites.getRosterSet().contains(m))
            {
                Member temp = invites.getMember(m.getRfid());
                invites.removeMember(temp);
                return attendees.addMember(temp);
            } else
            {
                System.err.println("Member was not invited!");
                return false;
            }
        } else
        {
            System.err.println("Invites is null!");
            return false;
        }
    }

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.invites);
        hash = 97 * hash + Objects.hashCode(this.attendees);
        hash = 97 * hash + Objects.hashCode(this.Description);
        hash = 97 * hash + Objects.hashCode(this.created);
        return hash;
    }

    @Override
    public boolean equals(Object obj) 
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (!Objects.equals(this.invites, other.invites)) {
            return false;
        }
        if (!Objects.equals(this.attendees, other.attendees)) {
            return false;
        }
        if (!Objects.equals(this.Description, other.Description)) {
            return false;
        }
        return true;
    }

    public MemberSet getInvites() {
        return invites;
    }

    /**
     * Only use to clear all previous invites
     * @param invites 
     */
    public void setInvites(MemberSet invites) {
        this.invites = invites;
    }

    public MemberSet getAttendees() {
        return attendees;
    }

    public void setAttendees(MemberSet attendees) {
        this.attendees = attendees;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public Date getTimeOfEvent() {
        return timeOfEvent;
    }

    public void setTimeOfEvent(Date timeOfEvent) {
        this.timeOfEvent = timeOfEvent;
    }
    
}
