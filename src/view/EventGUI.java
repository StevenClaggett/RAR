/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DataModel.*;
import RFID.RFIDCallable;
import RFID.RFIDCaller;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Chris Reilly
 */
public class EventGUI extends javax.swing.JFrame implements RFIDCallable {
    
    private static RARDocument doc;
    private static Event event;
    private static RFIDCaller caller;
    private static LaunchEventGUI leg;
    /**
     * Creates new form EventGUI
     */
    public EventGUI(RARDocument doc_, Event e_, LaunchEventGUI leg_) {
        this.doc = doc_;
        this.event = e_;
        this.leg = leg_;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        caller = new RFIDCaller(this);
        
        //this listener is for when the window closes, it will close the port.
        this.addWindowListener(new WindowAdapter()
            {
                @Override
                public void windowClosing(WindowEvent e)
                {
                    if (caller.serialPort != null)
                    {
                        caller.serialPort.close();
                    }
                    //System.out.println(event.getAttendees().getRosterSet().get(0));
                    
                    //we also want to move the event to completed events
                    
                    doc.getFutureEvents().remove(doc.getFutureEvents().indexOf(event));
                    doc.getCompletedEvents().add(event);
                    
                    // this calls the associated LaunchEventGUI to reset the future events table
                    leg.reset();
                    
                }
            });
        
        this.refreshInvitesTable();        
    }
    
    private void refreshInvitesTable()
    {
        ArrayList<Member> invites = this.event.getInvites().getRosterSet();
        
        String tableBuilder[][] = new String[invites.size()][2];
        
        for (int i = 0; i < invites.size(); i++)
        {
            tableBuilder[i][0] = invites.get(i).getfName();
            tableBuilder[i][1] = invites.get(i).getlName();
        }
        
        this.jTable1.setModel(new javax.swing.table.DefaultTableModel(tableBuilder, new String[]{
            "First", "Last"
        }));
        
    }
    @Override
    public void idWasScanned(String id)
    {
        long idTemp = 0;
        try 
        {
            idTemp = Long.parseLong(id, 16);    
        } catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(this, "Error in parsing long from text.");
            return;
        }
        
        
        
        
        if (doc.getRoster().getRosterSet().contains(new Member(idTemp)))
        {
            if (event.getInvites().getRosterSet().contains(new Member(idTemp)))
            {
                Member m = event.getInvites().getMember(idTemp);
                if(event.MemberAttended(m))
                {
                    this.WelcomeLabel.setText("<html><font color='green' size=35>Welcome " + m.getfName() + "</font></html>");
                    MemberName.setText(m.getfName()+" "+m.getlName() + " is counted present.");
                    this.refreshInvitesTable();
                } else
                {
                    MemberName.setText("Error.");
                }
            }else
            {
                Member m = doc.getRoster(idTemp);
                if (event.getAttendees().getRosterSet().contains(m))
                {
                    this.WelcomeLabel.setText("<html><font color='green' size=35>You are present already " + m.getfName() + "</font></html>");
                    MemberName.setText(m.getfName()+ " " +m.getlName() + " is already counted as present.");
                } else
                {
                    this.WelcomeLabel.setText("<html><font color='red' size=35>" + m.getfName() + " is not invited.</font></html>");
                    MemberName.setText(m.getfName()+ " " +m.getlName() + " was not invited.");
                }
            }  
        } else
        {
            this.WelcomeLabel.setText("<html><font color='red' size=35>Unrecognized ID</font></html>");
            MemberName.setText("The id " + idTemp + " does not exist in the system.");
        }
        
        
        // if no more invites, might as well close . ..
        
        /*
        This was causing a bug in slower computers
        if (event.getInvites().getRosterSet().isEmpty())
        {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Everyone invited has been counted present, would you like to close?","Attention",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION)
            {
                this.CloseButton.setEnabled(false);
                this.MemberName.setText("Please Wait, now closing. . .");
                this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            } 
            
        }
        */
            
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CloseButton = new javax.swing.JButton();
        MemberName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        WelcomeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        CloseButton.setText("Close Event");
        CloseButton.setActionCommand("CancelButton");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });

        MemberName.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        MemberName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MemberName.setText("Now listening for RFID scans . . .");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First", "Last"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Remaining Invites:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setText("Please scan your RFID device.");

        WelcomeLabel.setText("Scan your ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(MemberName, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(CloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(94, 94, 94)
                                .addComponent(WelcomeLabel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(WelcomeLabel)
                        .addGap(279, 279, 279)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MemberName, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        MemberName.getAccessibleContext().setAccessibleName("MemberName");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_CloseButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EventGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EventGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EventGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EventGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EventGUI(new RARDocument(), new Event("Event"), new LaunchEventGUI(mainPackage.NewClassRunner.getTestDoc())).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CloseButton;
    private javax.swing.JLabel MemberName;
    private javax.swing.JLabel WelcomeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
