/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiView;

import LibraryModel.Admin;
import LibraryModel.Client;
import LibraryModel.Item;
import LibraryModel.Message;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author tseow
 */
public class AdminWindow extends javax.swing.JFrame {

    private Admin a;
    private ArrayList<Client> Clients;
    private ArrayList<Item> Items;
    private ArrayList<Message> Messages;
    private LoginWindow logwin;
    private Item selectedRequestItem;
    private Message selectedMessage;
    private Client selectedClient;
    
    
    
    /**
     * Creates new form AdminWindow
     */
    public AdminWindow(Admin ad, ArrayList<Client> clnt, ArrayList<Item> items, ArrayList<Message> messages,LoginWindow lw) {
        initComponents();
        a = ad;
        Clients = clnt;
        Items = items;
        Messages = messages;
        logwin = lw;
        
        jWelcomeLabel.setText("Welcome Admin" + " (" + a.getFirstName() + " " + a.getLastName() + ")");
    
        loadMessages();
    
    }
    
    private void loadMessages()
    {
        
                
         String listData = "";
         String divider = "\n****\n";
         DefaultListModel  dataList = new DefaultListModel();
         for (Message m : Messages)
         {
            if (m.getRecipient().equals("ADMIN"))
            {
                listData = m.getMessageId() + ", " + m.getMessageSubject() + ", " + m.getSentDateTime();
                //listData += divider;
                dataList.addElement(listData);
            }
         }
         
         //Display all resources
         jMessageList.setModel(dataList);
    }
    
    private void refreshMessageDisplay()
    {
        jMessageBody.setText("");
        jMessageSubject.setText("");
        jSenderName.setText("");
        jClientInfo.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jWelcomeLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMessagePanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jMessageList = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        jMessageBody = new javax.swing.JTextArea();
        jOpenMessage = new javax.swing.JButton();
        jMessageSubject = new javax.swing.JTextField();
        jSenderName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jClientInfo = new javax.swing.JTextArea();
        jExtensionDecline = new javax.swing.JButton();
        jExtensionAccept = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 634, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jWelcomeLabel.setText("Welcome");

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(646, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jWelcomeLabel)
                    .addContainerGap(668, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jButton1)
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel7Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(jWelcomeLabel)
                    .addContainerGap(48, Short.MAX_VALUE)))
        );

        jMessageList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jMessageList);

        jMessageBody.setColumns(20);
        jMessageBody.setLineWrap(true);
        jMessageBody.setRows(5);
        jScrollPane5.setViewportView(jMessageBody);

        jOpenMessage.setText("Open Message");
        jOpenMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jOpenMessageActionPerformed(evt);
            }
        });

        jClientInfo.setColumns(20);
        jClientInfo.setRows(5);
        jScrollPane1.setViewportView(jClientInfo);

        jExtensionDecline.setText("Decline");
        jExtensionDecline.setEnabled(false);
        jExtensionDecline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExtensionDeclineActionPerformed(evt);
            }
        });

        jExtensionAccept.setText("Accept");
        jExtensionAccept.setEnabled(false);
        jExtensionAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExtensionAcceptActionPerformed(evt);
            }
        });

        jLabel4.setText("Client Information");

        jLabel5.setText("All Resources this Client is Borrowing");

        jButton2.setText("Delete Message");
        jButton2.setEnabled(false);

        javax.swing.GroupLayout jMessagePanelLayout = new javax.swing.GroupLayout(jMessagePanel);
        jMessagePanel.setLayout(jMessagePanelLayout);
        jMessagePanelLayout.setHorizontalGroup(
            jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMessagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addGroup(jMessagePanelLayout.createSequentialGroup()
                        .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jOpenMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(jScrollPane4))
                        .addGap(18, 18, 18)
                        .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSenderName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(jMessageSubject, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGap(18, 18, 18)
                        .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jMessagePanelLayout.createSequentialGroup()
                                .addComponent(jExtensionAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jExtensionDecline, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jMessagePanelLayout.setVerticalGroup(
            jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMessagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jMessagePanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSenderName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jMessagePanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jExtensionAccept)
                                    .addComponent(jExtensionDecline)))
                            .addGroup(jMessagePanelLayout.createSequentialGroup()
                                .addComponent(jMessageSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jOpenMessage)
                    .addComponent(jButton2))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Messages", jMessagePanel);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 833, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Send Reminders", jPanel6);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 833, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 566, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Newsletter", jPanel5);

        jTextField3.setText("jTextField3");

        jTextField4.setText("jTextField4");

        jButton4.setText("Add");

        jLabel1.setText("Set Resource ID");

        jLabel2.setText("Here you can add a new resource to the library");

        jLabel3.setText("New Resource's Title");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField4)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)))
                        .addGap(0, 580, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(42, 42, 42)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton4)
                .addContainerGap(323, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Resource", jPanel2);

        jTextField6.setText("jTextField6");

        jTextField7.setText("jTextField7");

        jTextField8.setText("jTextField8");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
                    .addComponent(jTextField7)
                    .addComponent(jTextField8))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(395, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create User", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 838, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        logwin.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jOpenMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenMessageActionPerformed
        
        if (Messages.isEmpty() == true)
        {
            JOptionPane.showMessageDialog(this, "There are no messages");
            return;
        }
        
        
        String message = jMessageList.getSelectedValue();
        String[] messageId = message.split(", ");
        String clientId = "";
        
        if (messageId[1].equals("Extension Request"))
        {
            jExtensionAccept.setEnabled(true);
            jExtensionDecline.setEnabled(true);
        }
        
        for (Message m : Messages)
        {
            if (m.getMessageId().equals(messageId[0]))
            {
                selectedMessage = m;
                jMessageBody.setText(m.getMessageBody());
                jMessageSubject.setText(m.getMessageSubject());
                break;
            }
        }
        
        messageId = messageId[0].split(":");
        
        for (Client c : Clients)
        {
            if (c.getId().equals(messageId[0]))
            {
               jSenderName.setText(c.getFirstName() + " " + c.getLastName());
               selectedClient = c;
            }
        }
        
        String borrowInfo = "";
        
        
        for (Item i : Items)
        {
            if (i.getId() == Integer.parseInt(messageId[1]))
            {
                selectedRequestItem = i;
               
                
            }
            
            if (i.getBorrowInfo().getUserID() == null) {
                continue;
            }
            
            if (i.getBorrowInfo().getUserID().equals(messageId[0]))
                {
                    borrowInfo += i.getId() + ", \"" + i.getTitle() + "\"";
                    borrowInfo += "\n\nBorrow Date: " + i.getBorrowInfo().getStartDate() + "\nReturn Date: " + i.getBorrowInfo().getReturnDate();
                    borrowInfo += "\n\nIs Item Overdue?: ";
                    if (i.getBorrowInfo().getIsOverdue() == true) {
                        borrowInfo += " Yes, by " + i.getBorrowInfo().returnDaysOverdue() + " days";
                        borrowInfo += "\nCurrently owes " + i.getBorrowInfo().returnOverdueAmountString() + " days";
                    }
                    else
                    {
                        borrowInfo += " No";
                    }
                
                    borrowInfo += "\nCurrent Extensions: " + i.getBorrowInfo().getExtension();
                
                    borrowInfo += "\n\n****\n\n";
                    jClientInfo.setText(borrowInfo);
                }
            
        }
        
        
        
        
        
        
    }//GEN-LAST:event_jOpenMessageActionPerformed

    private void jExtensionAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExtensionAcceptActionPerformed
        
        jExtensionAccept.setEnabled(false);
        jExtensionDecline.setEnabled(false);
        
        selectedRequestItem.getBorrowInfo().grantExtension(true, selectedClient);
        int index = 0;
        for (Message m : Messages)
        {
            if (m.getMessageId().equals(selectedMessage.getMessageId()))
            {
                break;
            }
            index++;
        }
        
        Messages.remove(index);
        loadMessages();
        refreshMessageDisplay();
        
          
    }//GEN-LAST:event_jExtensionAcceptActionPerformed

    private void jExtensionDeclineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExtensionDeclineActionPerformed
        jExtensionAccept.setEnabled(false);
        jExtensionDecline.setEnabled(false);
        
        selectedRequestItem.getBorrowInfo().grantExtension(false, selectedClient);
        
        int index = 0;
        for (Message m : Messages)
        {
            if (m.getMessageId().equals(selectedMessage.getMessageId()))
            {
                break;
            }
            index++;
        }
        
        Messages.remove(index);
        
        loadMessages();
        refreshMessageDisplay();
    }//GEN-LAST:event_jExtensionDeclineActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JTextArea jClientInfo;
    private javax.swing.JButton jExtensionAccept;
    private javax.swing.JButton jExtensionDecline;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextArea jMessageBody;
    private javax.swing.JList<String> jMessageList;
    private javax.swing.JPanel jMessagePanel;
    private javax.swing.JTextField jMessageSubject;
    private javax.swing.JButton jOpenMessage;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jSenderName;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JLabel jWelcomeLabel;
    // End of variables declaration//GEN-END:variables
}
