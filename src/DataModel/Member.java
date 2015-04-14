/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataModel;

import java.util.Objects;

/**
 * A Member represents a person who will use this system by attending events.
 * @author Steven
 */
public class Member implements java.io.Serializable
{

    
    private long rfid;
    private String fName;
    private String lName;
    private String email;
    private String otherInfo;

    public Member(long rfid) 
    {
        this.rfid = rfid;
        this.fName = "No Name";
        this.lName = "No Name";
        this.email = "No email specified.";
        this.otherInfo = "No other information available.";
    }
    
    public Member(long rfid, String fName, String lName, String email, String otherInfo) {
        this.rfid = rfid;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.otherInfo = otherInfo;
    }

    public Member(long rfid, String fName, String lName) {
        this.rfid = rfid;
        this.fName = fName;
        this.lName = lName;
        this.email = "No email specified.";
        this.otherInfo = "No other information available.";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.rfid ^ (this.rfid >>> 32));
        hash = 89 * hash + Objects.hashCode(this.fName);
        hash = 89 * hash + Objects.hashCode(this.lName);
        hash = 89 * hash + Objects.hashCode(this.email);
        hash = 89 * hash + Objects.hashCode(this.otherInfo);
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
        final Member other = (Member) obj;
        if (this.rfid != other.rfid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Member{" + "rfid=" + rfid + ", fName=" + fName + ", lName=" + lName + '}';
    }
    
    /**
     * @return the rfid
     */
    public long getRfid() {
        return rfid;
    }

    /**
     * @param rfid the rfid to set
     */
    public void setRfid(long rfid) {
        this.rfid = rfid;
    }

    /**
     * @return the fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * @param fName the fName to set
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @return the lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * @param lName the lName to set
     */
    public void setlName(String lName) {
        this.lName = lName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the otherInfo
     */
    public String getOtherInfo() {
        return otherInfo;
    }

    /**
     * @param otherInfo the otherInfo to set
     */
    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
    
}
