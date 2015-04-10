package RFID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * You must implement this to be able to be called by the RFIDCaller
 * @author Steven
 */
public interface RFIDCallable 
{

    /**
     * event listener for a scanned id
     * @param id
     */
    public void idWasScanned(String id);
}
