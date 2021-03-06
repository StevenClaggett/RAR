/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import DataModel.*;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Steven
 */
public class ReviewInfoGUI extends javax.swing.JFrame {
    
    private static RARDocument doc;
    private ArrayList<Event> events;
    Event eventToReview;
    /**
     * Creates new form ReviewInfoGUI
     */
    public ReviewInfoGUI(RARDocument doc_) 
    {
        this.doc = doc_;
        events = doc.getCompletedEvents();
        initComponents();
        initEventTable();
        this.deleteEventButton.setEnabled(false);
        this.exportButton.setEnabled(false);
        this.reviewButton.setEnabled(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
    }

    private void initEventTable()
    {
        String [][] tableBuilder = new String[events.size()][1];
        
        for (int i = 0; i < events.size(); i++)
        {
            tableBuilder[i][0] = events.get(i).getDescription();
        }
        
        this.completedEventsTable.setModel(new javax.swing.table.DefaultTableModel(tableBuilder, new String[]{
            "Name/Description of Event"
        }));   
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        reviewButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        completedEventsTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        attendedTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        didNotAttendTable = new javax.swing.JTable();
        deleteEventButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        reviewButton.setText("Review this Event");
        reviewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewButtonActionPerformed(evt);
            }
        });

        completedEventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Completed Events"
            }
        ));
        completedEventsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                completedEventsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(completedEventsTable);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Please select an event to review:");

        closeButton.setText("Close");
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
        });

        attendedTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(attendedTable);

        didNotAttendTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First", "Last"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(didNotAttendTable);

        deleteEventButton.setText("Delete Event");
        deleteEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEventButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Members who attended this event");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Members who did not attend this event");

        exportButton.setText("Export to Excel");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(reviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(311, 311, 311)
                                .addComponent(deleteEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deleteEventButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reviewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseClicked
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_closeButtonMouseClicked

    private void completedEventsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_completedEventsTableMouseClicked
        this.deleteEventButton.setEnabled(true);
        this.reviewButton.setEnabled(true);
    }//GEN-LAST:event_completedEventsTableMouseClicked

    private void deleteEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEventButtonActionPerformed
        
        if (this.completedEventsTable.getSelectedRow() >= 0)
        {
            Event eventToDelete = events.get(this.completedEventsTable.getSelectedRow());
            this.eventToReview = null;
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Do you really want to remove this event?"
                + "\nName: " + eventToDelete.getDescription(), "Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION)
            {
                doc.getCompletedEvents().remove(eventToDelete);
                this.deleteEventButton.setEnabled(false);
                this.reviewButton.setEnabled(false);
                this.exportButton.setEnabled(false);
                this.initEventTable();
                
                //these setModel calls just clear the tables if they happen to have something in them already
                
                this.attendedTable.setModel(new javax.swing.table.DefaultTableModel(new String[0][0], new String[]{
                "First", "Last"
                }));
            
                this.didNotAttendTable.setModel(new javax.swing.table.DefaultTableModel(new String[0][0], new String[]{
                "First", "Last"
                }));
                
            }
            
        }
    }//GEN-LAST:event_deleteEventButtonActionPerformed

    private void reviewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewButtonActionPerformed
        if (this.completedEventsTable.getSelectedRow() >= 0)
        {
            eventToReview = events.get(this.completedEventsTable.getSelectedRow());
            
            ArrayList<Member> attendees = eventToReview.getAttendees().getRosterSet();
            ArrayList<Member> noShows = eventToReview.getInvites().getRosterSet();
            
            String t1[][] = new String[attendees.size()][2];
            String t2[][] = new String[noShows.size()][2];
            
            for (int i = 0; i < attendees.size(); i++)
            {
                t1[i][0] = attendees.get(i).getfName();
                t1[i][1] = attendees.get(i).getlName();
            }
            
            for (int i = 0; i < noShows.size(); i++)
            {
                t2[i][0] = noShows.get(i).getfName();
                t2[i][1] = noShows.get(i).getlName();
            }
            
            this.attendedTable.setModel(new javax.swing.table.DefaultTableModel(t1, new String[]{
            "First", "Last"
            }));
            
            this.didNotAttendTable.setModel(new javax.swing.table.DefaultTableModel(t2, new String[]{
            "First", "Last"
            }));
            
            this.exportButton.setEnabled(true);
            
        }
    }//GEN-LAST:event_reviewButtonActionPerformed

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed
        if (this.eventToReview != null)
        {
            try 
            {
                DefaultTableModel dtm = new DefaultTableModel();
                Vector<String> cols = new Vector<String>();
                dtm.addColumn("First");
                dtm.addColumn("Last");
                dtm.addColumn("Email");
                dtm.addColumn("Attended");

                Vector<String> dtmrow = null;
                dtmrow = new Vector<>();
                dtmrow.add("First");
                dtmrow.add("Last");
                dtmrow.add("Email");
                dtmrow.add("Attended (Yes or No)");

                dtm.addRow(dtmrow);

                dtmrow = null;

                for (int i=0;i<this.eventToReview.getAttendees().getRosterSet().size();i++) 
                {
                    dtmrow = new Vector<>();

                    Member localM = this.eventToReview.getAttendees().getRosterSet().get(i);

                    dtmrow.add(localM.getfName());
                    dtmrow.add(localM.getlName());
                    dtmrow.add(localM.getEmail());
                    dtmrow.add("Yes");
                    
                    dtm.addRow(dtmrow);
                }
                
                for (int i=0;i<this.eventToReview.getInvites().getRosterSet().size();i++) 
                {
                    dtmrow = new Vector<>();

                    Member localM = this.eventToReview.getInvites().getRosterSet().get(i);

                    dtmrow.add(localM.getfName());
                    dtmrow.add(localM.getlName());
                    dtmrow.add(localM.getEmail());
                    dtmrow.add("No");
                    
                    dtm.addRow(dtmrow);
                }
                
                ////////////////////////
                Workbook wb = new HSSFWorkbook();
                CreationHelper createhelper = wb.getCreationHelper();
                Sheet sheet = wb.createSheet("Attendence Data for " + this.eventToReview.getDescription());
                Row row = null;
                Cell cell = null;
                for (int i=0;i<dtm.getRowCount();i++) {
                    row = sheet.createRow(i);
                    for (int j=0;j<dtm.getColumnCount();j++) {

                        cell = row.createCell(j);
                        cell.setCellValue((String) dtm.getValueAt(i, j));
                    }
                }

                
                try (FileOutputStream out = new FileOutputStream(".\\" + this.eventToReview.getDescription().replaceAll(" ", "") + ".xls")) {
                    wb.write(out);
                    System.out.println("Here!");
                }
            } catch (Exception e) {
            }
        }
            
        
    }//GEN-LAST:event_exportButtonActionPerformed

    void initializeEventsTable() {
        ArrayList<ArrayList<String>> compEvents = new ArrayList<>();
        
        ArrayList<Event> completedEventsList = doc.getCompletedEvents();
        
        
        
        
        
       
    }
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
            java.util.logging.Logger.getLogger(ReviewInfoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReviewInfoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReviewInfoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReviewInfoGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReviewInfoGUI(new RARDocument()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable attendedTable;
    private javax.swing.JButton closeButton;
    private javax.swing.JTable completedEventsTable;
    private javax.swing.JButton deleteEventButton;
    private javax.swing.JTable didNotAttendTable;
    private javax.swing.JButton exportButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton reviewButton;
    // End of variables declaration//GEN-END:variables
}
