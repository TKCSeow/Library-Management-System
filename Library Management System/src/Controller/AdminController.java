/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GuiView.AdminWindow;
import GuiView.LoginWindow;
import LibraryModel.Item.Book;
import LibraryModel.Item.DVD;
import LibraryModel.Item.Item;
import LibraryModel.Item.Magazine;
import LibraryModel.Item.Newspaper;
import LibraryModel.Message;
import LibraryModel.NewsletterSingleton;
import LibraryModel.Serialiser;
import LibraryModel.User.Admin;
import LibraryModel.User.Client;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author tseow
 */
public class AdminController {
    
    private AdminWindow aw;
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
    
    
    public AdminController(Admin ad, ArrayList<Client> clnt, ArrayList<Item> items, ArrayList<Message> messages,LoginWindow lw) {
       
        a = ad;
        Clients = clnt;
        Items = items;
        Messages = messages;
        logwin = lw;
        
        aw = new AdminWindow(this);
        
    }
    
    //Opens Admin GUI
    public void openWindow()
    {
        aw.setVisible(true);
    }
    
    //Setup
    public void setupWindow(JLabel jWelcomeLabel, JTextArea jNewsletterDisplay)
    {
        jWelcomeLabel.setText("Welcome Admin" + " (" + a.getFirstName() + " " + a.getLastName() + ")");
        NewsletterSingleton.getInstance().displayNewsletter(jNewsletterDisplay);
    }
    
    //Checks is message are already irrelevent and deletes them
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
    
    //Display all messages
    public void loadMessages(JList jMessageList)
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
    
    //Empties message textboxes
    public void refreshMessageDisplay(JTextField jMessageId, JTextArea jMessageBody,JTextField jMessageSubject, JTextField jSenderName, JTextArea jClientInfo)
    {
        jMessageId.setText("");
        jMessageBody.setText("");
        jMessageSubject.setText("");
        jSenderName.setText("");
        jClientInfo.setText("");
        
    }
    
    //Displays All Clients
    public void loadClients(JList jL)
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
    
    //Opens Client
    public void viewClient(JList jL, JTextField id, JTextField name, JTextArea info, JButton jSendMessage, JButton jSelectBorrowedItem)
    {

        
        if (Clients.isEmpty() == true)
        {
            JOptionPane.showMessageDialog(aw, "There are no clients");
            return;
        }
        if (jL.getSelectedValue() == null)
        {
            JOptionPane.showMessageDialog(aw, "Please select a Client");
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
        
    }
    
    //Display Client's Borrowed Resources
    public void loadBorrowedItems(JList jReminderBorrowList)
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
    
    //Opens Message
     public void openMessage(JButton jExtensionAccept, JButton jExtensionDecline, JButton jDeleteMessage, JList jMessageList, JTextField jMessageId, JTextArea jMessageBody,JTextField jMessageSubject, JTextField jSenderName, JTextArea jClientInfo) {                                             
        
        jExtensionAccept.setEnabled(false);
        jExtensionDecline.setEnabled(false);
        jDeleteMessage.setEnabled(false);
        
        if (Messages.isEmpty() == true)
        {
            JOptionPane.showMessageDialog(aw, "There are no messages");
            return;
        }
        
        
        String message = jMessageList.getSelectedValue().toString();
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
        
        
        
        
        
        
    }  
    
     //Send Message
    public void sendMessage(JTextField jSendMessageSubject, JTextArea jSendMessageBody)
    {            
        Message temp;
        
        temp = new Message("ADMIN", selectedClientReminder.getId(), jSendMessageSubject.getText(), jSendMessageBody.getText());      
    
        selectedClientReminder.getMessages().add(temp);
        
        JOptionPane.showMessageDialog(aw, "Message Sent");
            
        jSendMessageSubject.setText("");
        jSendMessageBody.setText("");
    }

    //Accept Extension
    public void extensionAccept(JButton jExtensionAccept, JButton jExtensionDecline) {                                                 
        
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
  
    } 
    
    //Decline Extension
    public void extensionDecline(JButton jExtensionAccept, JButton jExtensionDecline) {                                                  
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
 
    } 
    
    //Delete Message
    public void deleteMessage(JButton jDeleteMessage) {                                               
       
        Messages.remove(selectedMessage);
        jDeleteMessage.setEnabled(false);
        JOptionPane.showMessageDialog(aw, "Message Deleted");
        

    }  
    
    //Display Resources
    public void loadResources(JList jL)
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
    
    //Open a Resources
    public void viewResources(JList jResourceList, JTextField jViewResourceId, JTextField jViewResourceTitle, JTextField jViewResourceType, JTextArea jViewResourceBorrowInfo)
    {
        
        String infoText = "";        
        String[] info = jResourceList.getSelectedValue().toString().split(", ");
        
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
                    infoText += "\n\nWho is borrowing this resource: " + i.getBorrowInfo().getUserID(); 
                
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
    
    //Adds a new Resource
    public void addResource(JTextField jNewResourceId, JTextField jNewResourceTitle, JComboBox jNewResourceType) {                                             
        
        if (jNewResourceId.getText().equals("") || jNewResourceTitle.getText().equals("") || jNewResourceType.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(aw, "Missing Information");
            return;
        }
        
        int ResourceId = -1;
        
        try
        {
            ResourceId = Integer.parseInt(jNewResourceId.getText());
            
            //Check if id exists
            for (Item i : Items)
            {
                if (i.getId() == ResourceId)
                {
                    JOptionPane.showMessageDialog(aw, "This ID already exists!");
                    return;
                }
            }
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(aw, "This ID is not in the right format");
            return;
        } 
        
        
        String ResourceTitle = jNewResourceTitle.getText();
        int ResourceType = jNewResourceType.getSelectedIndex();
        
        //Create Resource based on type
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
        
        JOptionPane.showMessageDialog(aw, "Resource Added");
        jNewResourceId.setText("");
        jNewResourceTitle.setText("");
        jNewResourceType.setSelectedIndex(0);
        

    }     
    
    
    public void selectBorrowedItem(JList jReminderBorrowList, JTextField jReminderSelectedItem, JButton jSetReminder, JButton jCancelReminder)
    {
        
        if (jReminderBorrowList.getSelectedValue() == null)
        {
            JOptionPane.showMessageDialog(aw, "No Item Selected");
            return;
        }
        
        String item = jReminderBorrowList.getSelectedValue().toString();
        String[] itemId = item.split(", ");
        
        
        
        //set item
        for (Item i : Items)
        {
            if (i.getId() == Integer.parseInt(itemId[0]))
            {
               selectedBorrowedItem = i;
               jReminderSelectedItem.setText(item);
            }
        }
        
        
        //Enable buttons
        jSetReminder.setEnabled(true);
        jCancelReminder.setEnabled(true);
    }
    
    //Set Reminder
    public void setReminder(JRadioButton jReminderOnce,JRadioButton jReminderEvery3,JRadioButton jReminderEvery7)
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
            JOptionPane.showMessageDialog(aw, "Please Select an Option");
            return;
        }
        
        JOptionPane.showMessageDialog(aw, "Reminder Sent/Set");
    }
    
    //Cancel Reminder
    public void cancelReminder()
    {
        
            selectedBorrowedItem.getBorrowInfo().cancelReminders();
        
        
        JOptionPane.showMessageDialog(aw, "Reminder Cancelled");
    }
    
    //Create a new User
    public void createUser(JTextField jNewFName,JTextField jNewLName, JPasswordField jNewPass, JPasswordField jNewPass2, JTextArea jNewUserInfo)
    {
        if (jNewFName.getText().equals("") || jNewLName.getText().equals("") || jNewPass.getPassword().length == 0 ||  jNewPass2.getPassword().length == 0) {
            JOptionPane.showMessageDialog(aw, "Missing Information!");
            return;
        }
        
        
        boolean isExisting = false;
        
        int rnd = -1;
        
        String newPass1 = "";
        String newPass2 = "";
        char[] pass1;
        char[] pass2;
        
        Client temp;
        
        for (Client c : Clients)
        {
            rnd = new Random().nextInt(1000);
            
            if (!c.getId().equals("C" + rnd))
            {
                break;
            }
            else
            {
                rnd = -1;
            }
        }
        
        String passwordString = "";
        
        pass1 = jNewPass.getPassword();
        pass2 = jNewPass2.getPassword();
        
        jNewPass.setText("");
        jNewPass2.setText("");
        
        for (int i = 0; i < pass1.length; i++) {
            
            newPass1 += pass1[i];
        }
        
        for (int j = 0; j < pass2.length; j++) {
            newPass2 += pass2[j];
        }
        
        
        if (!newPass1.equals(newPass2))
        {
            JOptionPane.showMessageDialog(aw, "Passwords do not match!");
            return;
        }
        
        temp = new Client("C" + rnd, newPass1, jNewFName.getText(), jNewLName.getText());
        
        Clients.add(temp);
        
        //JOptionPane.showMessageDialog(this, "User Created");
        
        
        String newUserInfo = "";
        
        newUserInfo = "Welcome " + temp.getFirstName() + " " + temp.getLastName() + "\n\nYour new ID is: " + temp.getId() + "\n Please note and remember this ID as you will need it to login";
        
        
        jNewFName.setText("");
        jNewLName.setText("");

        jNewUserInfo.setText(newUserInfo);
        
       
    }
    
    //Update the Newsletter
    public void updateNewsletter(JTextField jNewsletterTitle, JTextArea jNewsletterBody, JTextArea jNewsletterDisplay) {                                                  
        
        if (jNewsletterTitle.getText().equals("") || jNewsletterBody.getText().equals("")) 
        {
            JOptionPane.showMessageDialog(aw, "Please complete before updating");
        }
        else
        {
            NewsletterSingleton.getInstance().updateNewsletter(jNewsletterTitle.getText(), jNewsletterBody.getText());
            NewsletterSingleton.getInstance().displayNewsletter(jNewsletterDisplay);
        }
        
        JOptionPane.showMessageDialog(aw, "Newsletter Updated");
        jNewsletterTitle.setText("");
        jNewsletterBody.setText("");
    }        
    
    //Logs user out
    public void logout()
    {
        aw.setVisible(false);
        logwin.setVisible(true);
        logwin.saveInformation();
       
    }
    
 
}
