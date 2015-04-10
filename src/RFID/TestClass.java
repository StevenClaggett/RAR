package RFID;


import java.io.*;
import java.util.*;
import gnu.io.*;
/**
 *
 * @author Steven
 */
public class TestClass implements RFIDCallable
{
    
    public static void main(String[] args) {
       TestClass t = new TestClass();
    }
    
    public TestClass()
    {
        RFIDCaller r = new RFIDCaller(this);
    }
    
    

    /*
    You must implement this in your gui form
    */
    @Override
    public void idWasScanned(String id) {
        System.out.println("This works+ " + id);
        try {
        System.out.println("ID integer is " + Integer.parseInt(id, 16));    
        } catch (Exception e) 
        {
            
        }
        
        System.out.println("Afetr");
    }
    
}
