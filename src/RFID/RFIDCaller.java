package RFID;

import java.io.*;
import java.util.*;
import gnu.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RFIDCaller implements Runnable, SerialPortEventListener 
{
    CommPortIdentifier portId;
    Enumeration portList;
    String uid = "";
    InputStream inputStream;
    SerialPort serialPort;
    Thread readThread;
    RFIDCallable t;

   /* public static void main(String[] args) {
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                 if (portId.getName().equals("COM3")) {
			//                if (portId.getName().equals("/dev/term/a")) {
                    //RUnner reader = new RUnner();
                     System.out.println("Yep");
                     //System.out.println(reader.getNextID());
                }
            }
        }
        System.out.println("Hello");
    }
    */

   
    
    public RFIDCaller(TestClass t_) 
    {
        t = t_;
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) 
        {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) 
            {
                 if (portId.getName().equals("COM3")) 
                 {
                    try 
                    {
                        serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
                    } catch (PortInUseException e) {System.out.println(e);}
                    try 
                    {
                        inputStream = serialPort.getInputStream();
                    } catch (IOException e) {System.out.println(e);}
                    try 
                    {
                        serialPort.addEventListener(this);
                    } catch (TooManyListenersException e) {System.out.println(e);}
                    serialPort.notifyOnDataAvailable(true);
                    try 
                    {
                        serialPort.setSerialPortParams(
                            9600,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE
                        );
                    } catch (UnsupportedCommOperationException e) {System.out.println(e);}
                    readThread = new Thread(this);
                    readThread.start();
                }
            }
        }
        
        
        
    }

    public void run() 
    {
        try 
        {
            Thread.sleep(20000);
        } catch (InterruptedException e) 
        {System.out.println(e);}
    }

    @Override
    public void serialEvent(SerialPortEvent event) 
    {
        switch(event.getEventType()) 
        {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                    byte[] readBuffer = new byte[40];

                    try 
                    {
                        int numBytes = 0; 
                        while (inputStream.available() < 8) 
                        {/*Empty While*/}
                        try 
                        {
                            Thread.sleep(20);//let the buffer catch up.
                        } catch (InterruptedException ex) 
                        {
                            Logger.getLogger(RFIDCaller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        numBytes = inputStream.read(readBuffer);
                        String id = new String(readBuffer).trim();
                        //System.out.print(id + "\n");
                        //uid = new String(readBuffer).trim() + "\n";
                        t.idWasScanned(id);
                    } catch (IOException e) 
                    {System.out.println(e);}
                break;
        }
    }
}