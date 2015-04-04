/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DataModel.*;
/**
 *
 * @author Steven
 */
public class NewClassRunner 
{
    
    public static void main(String[] args)
    {
        RARDocument doc = new RARDocument();
        doc.getFutureEvents().add(new Event("Class on WednesDay"));
        boolean b = doc.getFutureEvents().contains(new Event("Class on WednesDay"));
        doc.createEvent("Class on WednesDay");
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class on WednesDay"))).addInvite(new Member(1234, "Steven", "Claggett"));
        System.out.println(doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class on WednesDay"))).toString());
        Event ref = doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class on WednesDay")));
        ref.getAttendees().getMember(1234).setfName("Poop");
        
        doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class on WednesDay"))).MemberAttended(new Member(1234));
        System.out.println(doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class on WednesDay"))).toString());
        System.out.println(doc.getFutureEvents().get(doc.getFutureEvents().indexOf(new Event("Class on WednesDay"))).getAttendees().getMember(1234));
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
    }
    
}
