/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;
import java.util.ArrayList;
import java.util.Objects;
/**
 * A roster objects holds all valid members of a current system.
 * This is the list the user selects from when creating an invite list.
 * @author Steven
 */
public class MemberSet implements java.io.Serializable
{

    /**
     * Using an arraylist because this has to act like a map and a set simlultaneously
     * and because until.set don't have a get() mehtod.
     */
    private ArrayList<Member> rosterSet;

    public MemberSet() 
    {
        rosterSet = new ArrayList<>();   
    }

    public MemberSet(ArrayList<Member> rosterSet_) 
    {
        this.rosterSet = rosterSet_;
    }

    
    /**
     * 
     * @param rfid
     * @return null if member was not in set 
     */
    public Member getMember(int rfid)
    {
        Member temp = new Member(rfid);
        if (rosterSet.contains(temp))
        {
            return rosterSet.get(rosterSet.indexOf(temp));
        } else
        {
            System.err.println("Member not in Set!");
            return new Member(-1);
        }
    }
    
    /**
     * 
     * @param rfid
     * @param fName
     * @param lName
     * @return whether the member was added or not. 
     */
    public boolean addMember(int rfid, String fName, String lName)
    {
        Member temp = new Member(rfid, fName, lName);
        boolean InThere = rosterSet.contains(temp);
        if (InThere)
        {
            System.err.println("Member already in this set!");
            return false;
        } else
        {
            return rosterSet.add(temp);
        }
        
    }
    
    public boolean addMember(Member m)
    {
        boolean InThere = rosterSet.contains(m);
        if (InThere)
        {
            System.err.println("Member already in this set!");
            return false;
        } else
        {
            return rosterSet.add(m);
        }
        
    }
    
    public boolean removeMember(int rfid, String fName, String lName)
    {
        Member temp = new Member(rfid, fName, lName);
        boolean InThere = rosterSet.contains(temp);
        if (!InThere)
        {
            System.err.println("Member not in this set!");
            return false;
        } else
        {
            return rosterSet.remove(temp);
        }
    }
    
    public boolean removeMember(Member m)
    {
        boolean InThere = rosterSet.contains(m);
        if (!InThere)
        {
            System.err.println("Member not in this set!");
            return false;
        } else
        {
            return rosterSet.remove(m);
        }
    }
    
    
    public ArrayList<Member> getRosterSet() 
    {
        return rosterSet;
    }

    public void setRosterSet(ArrayList<Member> rosterSet) 
    {
        this.rosterSet = rosterSet;
    }

    @Override
    public int hashCode() 
    {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.rosterSet);
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
        final MemberSet other = (MemberSet) obj;
        if (!Objects.equals(this.rosterSet, other.rosterSet)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() 
    {
        return "Roster{" + "rosterSet=" + rosterSet + '}';
    }
    
}
