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
import javax.swing.JOptionPane;
/**
 *
 * @author Steven
 */
public class NewClassRunner 
{
    
    public static void main(String[] args)
    {
        RARDocument doc = readRAR();
        //System.out.println("RARDocument created, adding new event.");
        //doc.getFutureEvents().add(new Event("Class on WednesDay"));
        System.out.println(doc.getRoster().getMember(35));
        
        new view.LauncherGUI(doc).setVisible(true);
        //doc = getTestDoc();
        ////////////////////////////////////////////////////////////////////////
        /*
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
        try 
        {
            try (FileInputStream f = new FileInputStream("RARDocument.ser")) 
            {
                ObjectInput s = null;
                try 
                {
                    s = new ObjectInputStream(f);
                } catch (java.io.EOFException e) 
                {
                    System.err.println("First run requires creation of RARDocument.ser");
                    f.close();
                }

                if (s != null)
                {
                    doc = (RARDocument)s.readObject();
                }

            }
        } catch (FileNotFoundException ex) 
        {
            /* if file doesn't exist, just create it*/
            try 
            {

                JOptionPane.showMessageDialog(null, "Creating a new RARDocument.ser since none was found.");
                
                PrintWriter pw = new PrintWriter("RARDocument.ser");

                pw.close();


            } catch (FileNotFoundException ex1) 
            {
                //oops we are out of luck if we can't create the file....
                
                System.err.println("Very fatal error: for some reason the file RARDocument does not exist.");
                
            }

        } catch (IOException | ClassNotFoundException ex) 
        {
            if (ex instanceof java.io.EOFException)
            {
                /* if we get end of file exception, it just means we have 
                    read all the objects in the file 
                    the file input stream will be automatically closed
                */

            } else
            {
            }
        }

        if (doc == null)
        {
            doc = new RARDocument();
        }
        /*
        RARDocument doc = null;
        File file = new File("RARDocument.ser");
        if (file.exists() && file.isFile())
        {
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
                System.out.println("!!!!");
                return null;
            }
            catch(ClassNotFoundException c)
            {
                c.printStackTrace();
                System.out.println("@@@@");
                return null;
            }
        } else
        {
            doc = new RARDocument();
            new javax.swing.JOptionPane("No RARDocument.ser found, creating a new one...");
            System.out.println("doc was null");
        }
        if(doc == null)
        {
            
        }
        */
        return doc;
    }
    
    public static void writeRAR(RARDocument doc)
    {
        if (doc != null)
        {
            try 
            {
                try (FileOutputStream f = new FileOutputStream("RARDocument.ser")) //using try with resources
                {
                    ObjectOutput s = new ObjectOutputStream(f);

                    s.writeObject(doc);

                    s.flush();
                    f.close();
                }
            } catch (FileNotFoundException ex) 
            {
                
            } catch (IOException ex) 
            {
                
                ex.printStackTrace(System.out);
            }
        } else
        {
            System.err.println("Cannot writeout a null object.");
        }
        /*try
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
        }*/
    }
    
    public static RARDocument getTestDoc()
    {
        RARDocument doc = new RARDocument();
        
        doc.getRoster().addMember(new Member(1, "Steven", "Claggett"));
        doc.getRoster().addMember(new Member(2, "Jamal", "Almloussli"));
        doc.getRoster().addMember(new Member(3, "Barack", "Obama"));
        doc.getRoster().addMember(new Member(4, "Goodluck", "Johnson"));
        doc.getRoster().addMember(new Member(5, "Jennifer", "Anniston"));
        doc.getRoster().addMember(new Member(6, "Katy", "Perry"));
        doc.getRoster().addMember(new Member(7, "Steve", "Aoki"));
        doc.getRoster().addMember(new Member(8, "Brandon", "Baker"));
        doc.getRoster().addMember(new Member(9, "Chris", "Reilly"));
        doc.getRoster().addMember(new Member(10, "Lester", "Ferguson"));
        doc.getRoster().addMember(new Member(11, "Hillary", "Clinton"));
        doc.getRoster().addMember(new Member(12, "Glen", "Quagmire"));
        doc.getRoster().addMember(new Member(13, "John", "DeBluise"));
        doc.getRoster().addMember(new Member(14, "Scooby", "Doo"));
        doc.getRoster().addMember(new Member(15, "Peter", "Peterson"));
        doc.getRoster().addMember(new Member(16, "Jeremy", "Carmichael"));
        doc.getRoster().addMember(new Member(17, "Adrian", "Gomez"));
        doc.getRoster().addMember(new Member(18, "Hank", "Schraeder"));
        doc.getRoster().addMember(new Member(19, "Steve", "Gomez"));
        doc.getRoster().addMember(new Member(20, "Corey", "Zamora"));
        doc.getRoster().addMember(new Member(21, "Jeff", "Bezos"));
        doc.getRoster().addMember(new Member(22, "Eric", "Schmidt"));
        doc.getRoster().addMember(new Member(23, "Ken", "Thompson"));
        doc.getRoster().addMember(new Member(24, "Steve", "Jobs"));
        doc.getRoster().addMember(new Member(25, "Steve", "Wozniak"));
        doc.getRoster().addMember(new Member(26, "Bill", "Gates"));
        doc.getRoster().addMember(new Member(27, "Paul", "Allen"));
        doc.getRoster().addMember(new Member(28, "Chris", "Espinoza"));
        doc.getRoster().addMember(new Member(29, "Dennis", "Ritchie"));
        doc.getRoster().addMember(new Member(30, "Jayson", "Thieson"));
        doc.getRoster().addMember(new Member(31, "Rick", "Astley"));
        doc.getRoster().addMember(new Member(32, "Leory", "Jenkins"));
        doc.getRoster().addMember(new Member(33, "George", "Harrison"));
        doc.getRoster().addMember(new Member(34, "John", "Lennon"));
        doc.getRoster().addMember(new Member(35, "Steve", "Buscemi"));
        doc.getRoster().addMember(new Member(36, "Gandolf", "White"));
        doc.getRoster().addMember(new Member(37, "Walter", "White"));
        doc.getRoster().addMember(new Member(38, "Esther", "Little"));
        doc.getRoster().addMember(new Member(39, "Skylar", "White"));
        doc.getRoster().addMember(new Member(40, "Betty", "White"));
        
        doc.getFutureEvents().add(new Event("Class On Wednesday"));
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class On Wednesday"))).addInvite(new Member(1));
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class On Wednesday"))).addInvite(new Member(2));
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class On Wednesday"))).addInvite(new Member(3));
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class On Wednesday"))).addInvite(new Member(4));
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class On Wednesday"))).addInvite(new Member(5));
        
        doc.getFutureEvents().add(new Event("Class On Friday"));
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class On Friday"))).addInvite(new Member(6));
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class On Friday"))).addInvite(new Member(7));
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class On Friday"))).addInvite(new Member(8));
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class On Friday"))).addInvite(new Member(9));
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class On Friday"))).addInvite(new Member(0));
        
       
        
        return doc;
    }
    
}
