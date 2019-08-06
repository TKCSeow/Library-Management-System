/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiView;

import LibraryModel.Item.Book;
import LibraryModel.Item.DVD;
import LibraryModel.User.Admin;
import LibraryModel.User.Client;
import LibraryModel.Item.Item;
import LibraryModel.Item.Magazine;
import LibraryModel.Item.Newspaper;
import LibraryModel.Message;
import LibraryModel.Newsletter;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    private Item selectedResourceItem;
    private Item selectedBorrowedItem;
    private Message selectedMessage;
    private Client selectedClient;
    private Client selectedClientReminder;
    
    
    
    /**
     * Creates new form AdminWindow
     * @param ad
     * @param clnt
     * @param items
     * @param messages
     * @param lw
     */
    public AdminWindow(Admin ad, ArrayList<Client> clnt, ArrayList<Item> items, ArrayList<Message> messages,LoginWindow lw) {
        initComponents();
        a = ad;
        Clients = clnt;
        Items = items;
        Messages = messages;
        logwin = lw;
        
        jWelcomeLabel.setText("Welcome Admin" + " (" + a.getFirstName() + " " + a.getLastName() + ")");
        groupButton();
        loadMessages();
        loadClients(jReminderClientList);
        loadResources(jResourceList);
        
        Newsletter.getInstance().displayNewsletter(jNewsletterDisplay);
    
    }
    
    private void groupButton()
    {
        ButtonGroup bg = new ButtonGroup();
        
        bg.add(jReminderOnce);
        bg.add(jReminderEvery3);
        bg.add(jReminderEvery7);
    }
    
    private void checkMessages()
    {
        if (Messages.isEmpty() == true)
        {          
            return;
        }
        
         
            
         int[] indexToDelete = new int[100];
            int count = 0;
            for (Item i : Items)
            {
                
                if (i.getBorrowInfo().getUserID() == null)
                {
                     for (Message m : Messages)
                    {
                       String[] messageId = m.getMessageId().split(":"); 
                       
                        try{
                            if (Integer.parseInt(messageId[1]) == i.getId()) {
                                indexToDelete[count] = Messages.indexOf(m);
                                count++;
                            }
                        }
                        catch(NumberFormatException e)
                        {
                            continue;
                        }
                    }
                }
            }
            if (count != 0)
            {
                for (int i = 0; i < count; i++) {
                    Messages.remove(indexToDelete[i]);
                }
                
            }
            
            
         
    }
    
    private void loadMessages()
    {
        
         checkMessages();
         
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
        jMessageId.setText("");
        jMessageBody.setText("");
        jMessageSubject.setText("");
        jSenderName.setText("");
        jClientInfo.setText("");
        loadMessages();
    }
    
    private void loadClients(JList jL)
    {
        
         checkMessages();
         
         String listData = "";
         String divider = "\n****\n";
         DefaultListModel  dataList = new DefaultListModel();
         for (Client c : Clients)
         {
           
            listData = c.getId() + ", " + c.getFirstName() + ", " + c.getLastName();
            //listData += divider;
            dataList.addElement(listData);
            
         }
         
         //Display all resources
         jL.setModel(dataList);
    }
    
    private void viewClient(JList jL, JTextField id, JTextField name, JTextArea info)
    {
        jSendMessage.setEnabled(false);
        jSelectBorrowedItem.setEnabled(false);
        jSetReminder.setEnabled(false);
        jCancelReminder.setEnabled(false);
        
        if (Clients.isEmpty() == true)
        {
            JOptionPane.showMessageDialog(this, "There are no clients");
            return;
        }
        if (jL.getSelectedValue() == null)
        {
            JOptionPane.showMessageDialog(this, "Please select a Client");
            return;
        }
        
        
        String client = jL.getSelectedValue().toString();
        String[] clientId = client.split(", ");
        
        
        id.setText(clientId[0]);
        
        
        for (Client c : Clients)
        {
            if (c.getId().equals(clientId[0]))
            {
               id.setText(c.getId());
               name.setText(c.getFirstName() + " " + c.getLastName());
               selectedClientReminder = c;    
            }
        }
        
        
        
        String borrowInfo = "";
        
        
        info.setText("Not borrowing anything");
        for (Item i : Items)
        {
            
            if (i.getBorrowInfo().getUserID() == null) {
                continue;
            }
            
            if (i.getBorrowInfo().getUserID().equals(clientId[0]))
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
                    info.setText(borrowInfo);
                }
            
        }
        
        jSendMessage.setEnabled(true);
        jSelectBorrowedItem.setEnabled(true);
        loadBorrowedItems();
    }
    
    private void loadBorrowedItems()
    {
         String listData = "";
         DefaultListModel  dataList = new DefaultListModel();
         for (Item i : Items)
         {
             if (i.getBorrowInfo().getUserID() == null) {
                 continue;
             }
             
             if (i.getBorrowInfo().getUserID().equals(selectedClientReminder.getId())) {
                 listData = i.getId() + ", " + i.getTitle() + ", " + i.getItemType();
                 dataList.addElement(listData);
             }            
         }
         
         //Display all resources
         jReminderBorrowList.setModel(dataList);
         
    }
    
    private void sendMessage()
    {            
        Message temp;
        
        temp = new Message("ADMIN", selectedClientReminder.getId(), jSendMessageSubject.getText(), jSendMessageBody.getText());      
    
        selectedClientReminder.getMessages().add(temp);
        
        JOptionPane.showMessageDialog(this, "Message Sent");
            
        jSendMessageSubject.setText("");
        jSendMessageBody.setText("");
    }

    private void loadResources(JList jL)
    {
        
         checkMessages();
         
         String listData = "";
         String divider = "\n****\n";
         DefaultListModel  dataList = new DefaultListModel();
         for (Item i : Items)
         {
           
            listData = i.getId() + ", " + i.getTitle() + ", " + i.getItemType();
            //listData += divider;
            dataList.addElement(listData);
            
         }
         
         //Display all resources
         jL.setModel(dataList);
    }
    
    private void viewResources()
    {
        
        String infoText = "";        
        String[] info = jResourceList.getSelectedValue().split(", ");
        
        for (Item i : Items)
        {
            if (Integer.parseInt(info[0]) == i.getId())
            {
                selectedResourceItem = i;
                                              
                jViewResourceId.setText(i.getId() + "");
                jViewResourceTitle.setText(i.getTitle());
                jViewResourceType.setText(i.getItemType().toString());
                
                if (i.getBorrowInfo().getIsBorrowed() == false) {
                    infoText += "*In Stock*";
                    
                    infoText += "\n\nNot being borrowed";
                    
                    
                }
                else
                {
                    infoText += "\n\n*Not in Stock*"; 
                
                
                    infoText += "\n\nBorrow Date: " + i.getBorrowInfo().getStartDate() + "\nReturn Date: " + i.getBorrowInfo().getReturnDate();
                    infoText += "\n\nIs Item Overdue?: ";
                    if (i.getBorrowInfo().getIsOverdue() == true) {
                        infoText += " Yes, by " + i.getBorrowInfo().returnDaysOverdue() + " days";
                        infoText += "\nCurrently owes " + i.getBorrowInfo().returnOverdueAmountString() + " days";
                    }
                    else
                    {
                        infoText += " No";
                    }
                
                    infoText += "\nCurrent Extensions: " + i.getBorrowInfo().getExtension();
                
                    if (i.getRating().getUserRatingList().isEmpty() == true)
                    {
                        infoText += "\n\nUser Rating: 0 (No Ratings Yet)";
                    }
                    else
                    {
                        infoText += "\n\nUser Rating: " + i.getRating().getAverageScore() + " (" + i.getRating().getTotalUsersThatRated() + ")";
                    }
                
                    
                }
            }
        }
        
        jViewResourceBorrowInfo.setText(infoText);
        jViewResourceBorrowInfo.setCaretPosition(0);
        
        
    }
    
    private void selectBorrowedItem()
    {
        
        if (jReminderBorrowList.getSelectedValue() == null)
        {
            JOptionPane.showMessageDialog(this, "No Item Selected");
            return;
        }
        
        String item = jReminderBorrowList.getSelectedValue();
        String[] itemId = item.split(", ");
        
        
        
        
        for (Item i : Items)
        {
            if (i.getId() == Integer.parseInt(itemId[0]))
            {
               selectedBorrowedItem = i;
               jReminderSelectedItem.setText(item);
            }
        }
        
        jSetReminder.setEnabled(true);
        jCancelReminder.setEnabled(true);
    }
    
    private void setReminder()
    {
        if (jReminderOnce.isSelected())
        {
            selectedBorrowedItem.getBorrowInfo().setReminder(0, selectedClientReminder);
        }
        else if (jReminderEvery3.isSelected())
        {
            selectedBorrowedItem.getBorrowInfo().setReminder(3, selectedClientReminder);
        }
        else if (jReminderEvery7.isSelected())
        {
            selectedBorrowedItem.getBorrowInfo().setReminder(7, selectedClientReminder);
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please Select an Option");
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Reminder Sent/Set");
    }
    
    private void cancelReminder()
    {
        
            selectedBorrowedItem.getBorrowInfo().cancelReminders();
        
        
        JOptionPane.showMessageDialog(this, "Reminder Cancelled");
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
        jDeleteMessage = new javax.swing.JButton();
        jMessageId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jReminderClientList = new javax.swing.JList<>();
        jTextField1 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jReminderViewClient = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        jReminderClientInfo = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        jSendMessageBody = new javax.swing.JTextArea();
        jSendMessage = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jReminderOnce = new javax.swing.JRadioButton();
        jReminderEvery3 = new javax.swing.JRadioButton();
        jReminderEvery7 = new javax.swing.JRadioButton();
        jSetReminder = new javax.swing.JButton();
        jCancelReminder = new javax.swing.JButton();
        jSendMessageSubject = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jReminderClientName = new javax.swing.JTextField();
        jReminderClientId = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jReminderBorrowList = new javax.swing.JList<>();
        jSelectBorrowedItem = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jReminderSelectedItem = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jNewsletterDisplay = new javax.swing.JTextArea();
        jNewsletterTitle = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jNewsletterBody = new javax.swing.JTextArea();
        jUpdateNewsletter = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jNewResourceId = new javax.swing.JTextField();
        jNewResourceTitle = new javax.swing.JTextField();
        jAddResource = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jNewResourceType = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jResourceList = new javax.swing.JList<>();
        jViewResourceId = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jViewResourceTitle = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jViewResourceType = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jViewResourceBorrowInfo = new javax.swing.JTextArea();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jViewResource = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

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
                .addContainerGap()
                .addComponent(jWelcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jWelcomeLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMessageList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jMessageList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
        jClientInfo.setLineWrap(true);
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

        jDeleteMessage.setText("Delete Message");
        jDeleteMessage.setEnabled(false);
        jDeleteMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteMessageActionPerformed(evt);
            }
        });

        jLabel6.setText("Message ID:");

        jLabel7.setText("Sender Name:");

        jLabel8.setText("Subject:");

        javax.swing.GroupLayout jMessagePanelLayout = new javax.swing.GroupLayout(jMessagePanel);
        jMessagePanel.setLayout(jMessagePanelLayout);
        jMessagePanelLayout.setHorizontalGroup(
            jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMessagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jOpenMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(jScrollPane4))
                .addGap(18, 18, 18)
                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jMessagePanelLayout.createSequentialGroup()
                        .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jMessagePanelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jMessagePanelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSenderName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jMessageSubject, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jMessagePanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jMessageId, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jMessagePanelLayout.createSequentialGroup()
                        .addComponent(jExtensionAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jExtensionDecline, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jDeleteMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(183, Short.MAX_VALUE))
        );
        jMessagePanelLayout.setVerticalGroup(
            jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMessagePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jMessagePanelLayout.createSequentialGroup()
                        .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jMessagePanelLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addGroup(jMessagePanelLayout.createSequentialGroup()
                                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jMessageId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSenderName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jMessagePanelLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jExtensionAccept)
                                    .addComponent(jExtensionDecline)))
                            .addGroup(jMessagePanelLayout.createSequentialGroup()
                                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jMessageSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jMessagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jOpenMessage)
                    .addComponent(jDeleteMessage))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Messages", jMessagePanel);

        jReminderClientList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jReminderClientList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(jReminderClientList);

        jRadioButton1.setText("Search by ID");

        jRadioButton2.setText("Search by Name");

        jReminderViewClient.setText("View Client");
        jReminderViewClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jReminderViewClientActionPerformed(evt);
            }
        });

        jReminderClientInfo.setEditable(false);
        jReminderClientInfo.setColumns(20);
        jReminderClientInfo.setRows(5);
        jScrollPane8.setViewportView(jReminderClientInfo);

        jSendMessageBody.setColumns(20);
        jSendMessageBody.setRows(5);
        jScrollPane9.setViewportView(jSendMessageBody);

        jSendMessage.setText("Send");
        jSendMessage.setEnabled(false);
        jSendMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSendMessageActionPerformed(evt);
            }
        });

        jLabel18.setText("Send Reminder for Overdue Books");

        jReminderOnce.setText("Once");

        jReminderEvery3.setText("Every three days");

        jReminderEvery7.setText("Every 7 days");

        jSetReminder.setText("Send/Set");
        jSetReminder.setEnabled(false);
        jSetReminder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSetReminderActionPerformed(evt);
            }
        });

        jCancelReminder.setText("Cancel Reminder");
        jCancelReminder.setEnabled(false);
        jCancelReminder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelReminderActionPerformed(evt);
            }
        });

        jLabel19.setText("Subject:");

        jLabel20.setText("Send Message to Client");

        jReminderClientName.setEditable(false);

        jReminderClientId.setEditable(false);

        jLabel21.setText("Name:");

        jLabel22.setText("ID:");

        jScrollPane12.setViewportView(jReminderBorrowList);

        jSelectBorrowedItem.setText("Select Item");
        jSelectBorrowedItem.setEnabled(false);
        jSelectBorrowedItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSelectBorrowedItemActionPerformed(evt);
            }
        });

        jLabel28.setText("Seleted Item:");

        jReminderSelectedItem.setEditable(false);

        jLabel29.setText("Resources borrowed");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jReminderViewClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2))
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jReminderClientId, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jReminderClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSendMessageSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSendMessage))
                    .addComponent(jSelectBorrowedItem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jReminderOnce)
                            .addComponent(jReminderEvery3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addComponent(jReminderEvery7)
                                .addGap(101, 101, 101)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCancelReminder, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSetReminder, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jReminderSelectedItem))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel29))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jReminderClientId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jReminderClientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jReminderViewClient))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSelectBorrowedItem)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jReminderSelectedItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jReminderOnce)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jReminderEvery3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jReminderEvery7)
                                .addGap(1, 1, 1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jSetReminder)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCancelReminder)))
                        .addGap(32, 32, 32)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSendMessageSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSendMessage)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Send Reminders/Check Client", jPanel6);

        jNewsletterDisplay.setColumns(20);
        jNewsletterDisplay.setRows(5);
        jScrollPane2.setViewportView(jNewsletterDisplay);

        jNewsletterBody.setColumns(20);
        jNewsletterBody.setRows(5);
        jScrollPane3.setViewportView(jNewsletterBody);

        jUpdateNewsletter.setText("Update");
        jUpdateNewsletter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdateNewsletterActionPerformed(evt);
            }
        });

        jLabel13.setText("Current Newsletter");

        jLabel14.setText("Update Newsletter");

        jLabel15.setText("Title:");

        jLabel16.setText("Main:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jNewsletterTitle))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jUpdateNewsletter))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(402, 402, 402))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jNewsletterTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jUpdateNewsletter)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Newsletter", jPanel5);

        jNewResourceTitle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewResourceTitleActionPerformed(evt);
            }
        });

        jAddResource.setText("Add");
        jAddResource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddResourceActionPerformed(evt);
            }
        });

        jLabel1.setText("Set Resource ID");

        jLabel2.setText("Here you can view and add resources to the library");

        jLabel3.setText("New Resource's Title");

        jNewResourceType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Book", "DVD", "Newspaper", "Magazine" }));
        jNewResourceType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewResourceTypeActionPerformed(evt);
            }
        });

        jLabel17.setText("New Resource Type");

        jResourceList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane10.setViewportView(jResourceList);

        jLabel23.setText("Resource ID");

        jLabel24.setText("Resource Name");

        jLabel25.setText("Resource Type");

        jViewResourceBorrowInfo.setColumns(20);
        jViewResourceBorrowInfo.setRows(5);
        jScrollPane11.setViewportView(jViewResourceBorrowInfo);

        jLabel26.setText("Add New Resource");

        jLabel27.setText("Borrow Information");

        jViewResource.setText("View Resource");
        jViewResource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jViewResourceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addGap(0, 612, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10)
                            .addComponent(jViewResource, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jViewResourceId)
                                .addComponent(jLabel23)
                                .addComponent(jLabel24)
                                .addComponent(jViewResourceTitle)
                                .addComponent(jLabel25)
                                .addComponent(jViewResourceType, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(jNewResourceTitle, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jNewResourceId, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel17)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jNewResourceType, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                                    .addComponent(jAddResource))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jNewResourceId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jViewResourceId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jNewResourceTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jViewResourceTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jNewResourceType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jAddResource)
                            .addComponent(jViewResourceType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jViewResource)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Add/Check Resource", jPanel2);

        jButton2.setText("Create");

        jLabel9.setText("Enter First Name");

        jLabel10.setText("Enter Last Name");

        jLabel11.setText("Create New Password");

        jLabel12.setText("Re-enter New Password");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane7.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
                    .addComponent(jTextField7)
                    .addComponent(jTextField8)
                    .addComponent(jTextField2)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(0, 762, Short.MAX_VALUE))
                    .addComponent(jScrollPane7))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(10, 10, 10)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jButton2)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create User", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        logwin.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jOpenMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jOpenMessageActionPerformed
        
        jExtensionAccept.setEnabled(false);
        jExtensionDecline.setEnabled(false);
        jDeleteMessage.setEnabled(false);
        
        if (Messages.isEmpty() == true)
        {
            JOptionPane.showMessageDialog(this, "There are no messages");
            return;
        }
        
        
        String message = jMessageList.getSelectedValue();
        String[] messageId = message.split(", ");
        String clientId = "";
        
        jMessageId.setText(messageId[0]);
        
        if (messageId[1].equals("Extension Request"))
        {
            jExtensionAccept.setEnabled(true);
            jExtensionDecline.setEnabled(true);
        }
        
        if (messageId[1].equals("Resource Request"))
        {
            jDeleteMessage.setEnabled(true);
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
        int compareId;
        
        if (messageId[1].contains("R"))
        {
            compareId = -1;
        }
        else
        {
            compareId = Integer.parseInt(messageId[1]);
        }
        
        jClientInfo.setText("Not borrowing anything");
        for (Item i : Items)
        {
            if (i.getId() == compareId)
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
        
        
        refreshMessageDisplay();
    }//GEN-LAST:event_jExtensionDeclineActionPerformed

    private void jUpdateNewsletterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdateNewsletterActionPerformed
        
        if (jNewsletterTitle.getText().equals("") || jNewsletterBody.getText().equals("")) 
        {
            JOptionPane.showMessageDialog(this, "Please complete before updating");
        }
        else
        {
            Newsletter.getInstance().updateNewsletter(jNewsletterTitle.getText(), jNewsletterBody.getText());
            Newsletter.getInstance().displayNewsletter(jNewsletterDisplay);
        }
        
        JOptionPane.showMessageDialog(this, "Newsletter Updated");
        jNewsletterTitle.setText("");
        jNewsletterBody.setText("");
    }//GEN-LAST:event_jUpdateNewsletterActionPerformed

    private void jDeleteMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteMessageActionPerformed
       
        Messages.remove(selectedMessage);
        jDeleteMessage.setEnabled(false);
        JOptionPane.showMessageDialog(this, "Message Deleted");
        
        refreshMessageDisplay();
    }//GEN-LAST:event_jDeleteMessageActionPerformed

    private void jNewResourceTitleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewResourceTitleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNewResourceTitleActionPerformed

    private void jNewResourceTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewResourceTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNewResourceTypeActionPerformed

    private void jAddResourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddResourceActionPerformed
        
        if (jNewResourceId.getText().equals("") || jNewResourceTitle.getText().equals("") || jNewResourceType.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Missing Information");
            return;
        }
        
        int ResourceId = -1;
        
        try
        {
            ResourceId = Integer.parseInt(jNewResourceId.getText());
            
            for (Item i : Items)
            {
                if (i.getId() == ResourceId)
                {
                    JOptionPane.showMessageDialog(this, "This ID already exists!");
                    return;
                }
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this, "This ID is not in the right format");
            return;
        } 
        
        
        String ResourceTitle = jNewResourceTitle.getText();
        int ResourceType = jNewResourceType.getSelectedIndex();
        
        switch (ResourceType) {
            case 1:
                {
                    Book temp = new Book(ResourceId, ResourceTitle);
                    Items.add(temp);
                    break;
                }
            case 2:
                {
                    DVD temp = new DVD(ResourceId, ResourceTitle);
                    Items.add(temp);
                    break;
                }
            case 3:
                {
                    Newspaper temp = new Newspaper(ResourceId, ResourceTitle);
                    Items.add(temp);
                    break;
                }
            case 4:
                {
                    Magazine temp = new Magazine(ResourceId, ResourceTitle);
                    Items.add(temp);
                    break;
                }
            default:
                break;
        }
        
        JOptionPane.showMessageDialog(this, "Resource Added");
        jNewResourceId.setText("");
        jNewResourceTitle.setText("");
        jNewResourceType.setSelectedIndex(0);
        

    }//GEN-LAST:event_jAddResourceActionPerformed

    private void jReminderViewClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jReminderViewClientActionPerformed
        viewClient(jReminderClientList, jReminderClientId, jReminderClientName, jReminderClientInfo);
    }//GEN-LAST:event_jReminderViewClientActionPerformed

    private void jSendMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSendMessageActionPerformed
        sendMessage();
    }//GEN-LAST:event_jSendMessageActionPerformed

    private void jViewResourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jViewResourceActionPerformed
        viewResources();
    }//GEN-LAST:event_jViewResourceActionPerformed

    private void jSelectBorrowedItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSelectBorrowedItemActionPerformed
        selectBorrowedItem();
    }//GEN-LAST:event_jSelectBorrowedItemActionPerformed

    private void jSetReminderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSetReminderActionPerformed
        setReminder();
    }//GEN-LAST:event_jSetReminderActionPerformed

    private void jCancelReminderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelReminderActionPerformed
        cancelReminder();
    }//GEN-LAST:event_jCancelReminderActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddResource;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jCancelReminder;
    private javax.swing.JTextArea jClientInfo;
    private javax.swing.JButton jDeleteMessage;
    private javax.swing.JButton jExtensionAccept;
    private javax.swing.JButton jExtensionDecline;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextArea jMessageBody;
    private javax.swing.JTextField jMessageId;
    private javax.swing.JList<String> jMessageList;
    private javax.swing.JPanel jMessagePanel;
    private javax.swing.JTextField jMessageSubject;
    private javax.swing.JTextField jNewResourceId;
    private javax.swing.JTextField jNewResourceTitle;
    private javax.swing.JComboBox<String> jNewResourceType;
    private javax.swing.JTextArea jNewsletterBody;
    private javax.swing.JTextArea jNewsletterDisplay;
    private javax.swing.JTextField jNewsletterTitle;
    private javax.swing.JButton jOpenMessage;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JList<String> jReminderBorrowList;
    private javax.swing.JTextField jReminderClientId;
    private javax.swing.JTextArea jReminderClientInfo;
    private javax.swing.JList<String> jReminderClientList;
    private javax.swing.JTextField jReminderClientName;
    private javax.swing.JRadioButton jReminderEvery3;
    private javax.swing.JRadioButton jReminderEvery7;
    private javax.swing.JRadioButton jReminderOnce;
    private javax.swing.JTextField jReminderSelectedItem;
    private javax.swing.JButton jReminderViewClient;
    private javax.swing.JList<String> jResourceList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JButton jSelectBorrowedItem;
    private javax.swing.JButton jSendMessage;
    private javax.swing.JTextArea jSendMessageBody;
    private javax.swing.JTextField jSendMessageSubject;
    private javax.swing.JTextField jSenderName;
    private javax.swing.JButton jSetReminder;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JButton jUpdateNewsletter;
    private javax.swing.JButton jViewResource;
    private javax.swing.JTextArea jViewResourceBorrowInfo;
    private javax.swing.JTextField jViewResourceId;
    private javax.swing.JTextField jViewResourceTitle;
    private javax.swing.JTextField jViewResourceType;
    private javax.swing.JLabel jWelcomeLabel;
    // End of variables declaration//GEN-END:variables
}
