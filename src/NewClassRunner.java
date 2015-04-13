/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DataModel.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier; 
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent; 
import gnu.io.SerialPortEventListener; 
import java.util.Enumeration;
import java.io.*;
/**
 *
 * @author Steven
 */
public class NewClassRunner 
{
    
    public static void main(String[] args)
    {
        RARDocument doc = readRAR();
        System.out.println("RARDocument created, adding new event.");
        //doc.getFutureEvents().add(new Event("Class on WednesDay"));
        
        ////////////////////////////////////////////////////////////////////////
        
        //System.out.println("Event Added. Checking to confirm event added");
        boolean b = doc.getFutureEvents().contains(new Event("Class on WednesDay"));
        if(b)
            System.out.println("Event succeddfully added.");
        else
            System.out.println("Event not successfully added");
        
        ////////////////////////////////////////////////////////////////////////
        
        System.out.println("Testing createEvent with previous event. Should fail.");
        doc.createEvent("Class on WednesDay");
        ////////////////////////////////////////////////////////////////////////
        
        System.out.println("Adding a member to the event, then printing event.");
        doc.getFutureEvents("Class on WednesDay").addInvite(new Member(1234, "Steven", "Claggett"));
        System.out.println(doc.getFutureEvents("Class on WednesDay").toString());
        
        ////////////////////////////////////////////////////////////////////////
        
        System.out.println("Attempting to set the fName of an attendee.");
        Event ref = doc.getFutureEvents("Class on WednesDay");
        ref.getInvites().getMember(1234).setfName("Poop");
        
        ////////////////////////////////////////////////////////////////////////
        
        System.out.println("Setting an attendee as attended. Then printing both event and Member");
        doc.getFutureEvents("Class on WednesDay").MemberAttended(new Member(1234));
        System.out.println(doc.getFutureEvents("Class on WednesDay").toString());
        System.out.println(doc.getFutureEvents("Class on WednesDay").getAttendees().getMember(1234));
        
        /*DataModel.Member m = new DataModel.Member(1234, "Steven", "Claggett");
        DataModel.MemberSet ms = new DataModel.MemberSet();
        ms.addMember(1234, "This", "Guy");
        ms.addMember(1235, "Thsssssis", "Guy");
        DataModel.Event t = new DataModel.Event("Class for today");
        t.addInvite(new DataModel.Member(111));
        t.MemberAttended(m);
        System.out.println(t.getAttendees().getMember(1234) + " this line");
        System.out.println(ms.getMember(1235).toString());
        System.out.println(m.toString());
        */
        
        
        writeRAR(doc);
    }
    
    public static RARDocument readRAR()
    {
        RARDocument doc = null;
        File file = new File("RARDocument.ser");
        try
        {
            file.createNewFile();
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            doc = (RARDocument) in.readObject();
            in.close();
            fileIn.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
            return null;
        }
        catch(ClassNotFoundException c)
        {
            c.printStackTrace();
            return null;
        }
        if(doc == null)
            doc = new RARDocument();
        
        return doc;
    }
    
    public static void writeRAR(RARDocument doc)
    {
        try
        {
            File file = new File("RARDocument.ser");
            file.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(doc);
            out.close();
        }
        catch(IOException i)
        {
            i.printStackTrace();
        }
    }
}
