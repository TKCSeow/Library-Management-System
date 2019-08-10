/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GuiView.ClientWindow;
import GuiView.LoginWindow;
import LibraryModel.Item.Item;
import LibraryModel.Message;
import LibraryModel.NewsletterSingleton;
import LibraryModel.User.Client;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author tseow
 */
public class ClientController {

    private Client c;
    private ArrayList<Item> Items = new ArrayList<>();
    private ArrayList<Item> BorrowedItems;
    private ArrayList<Message> adminMessages;
    private ArrayList<Message> userMessages;
    private Item selectedItem;
    private Item selectedBorrowedItem;
    private LoginWindow logwin;
    private ClientWindow cw;
   
    public ClientController(Client clnt, ArrayList<Item> items, ArrayList<Message> AdminMessages, LoginWindow lw) {
        
        
        logwin = lw;
        c = clnt;
        
        Items = items;
        userMessages = c.getMessages();
        adminMessages = AdminMessages;
        
        BorrowedItems = new ArrayList<>();
        cw = new ClientWindow(this);

        
    }
    
    //Open Client GUI
    public void openWindow()
    {
        cw.setVisible(true);
    }
    
    //Setup
    public void setupWindow(JLabel jWelcomeLabel, JList jResourceList, JTextArea jNewsletterDisplay)
    {
        // Displays Welcome message with user's names
         String welcomeText = "Welcome " + c.getFirstName() + " " + c.getLastName();
         jWelcomeLabel.setText(welcomeText);
         
         String listData = "";
         DefaultListModel  dataList = new DefaultListModel();
         for (Item i : Items)
         {
             
             listData = i.getId() + ", " + i.getTitle() + ", " + i.getItemType();
             dataList.addElement(listData);
         }
         
         //Display all resources
         jResourceList.setModel(dataList);
         
         NewsletterSingleton.getInstance().displayNewsletter(jNewsletterDisplay);
        NewsletterSingleton.getInstance().checkIfNewsletterIsRead(c, cw);
    }
    
    //Set Resources Client has borrowed
    public void updateBorrowedItems()
    {
        BorrowedItems.clear();
        
        for (Item i : Items)
        {
            if (i.getBorrowInfo().getUserID() != null) 
            {
                if (i.getBorrowInfo().getUserID().equals(c.getId())) {
                    BorrowedItems.add(i);
                }
            }
        }
    }
   
    //Displays Resources Client has borrowed
    public void updateBorrowedItemsDisplay(JList jBorrowedItemsList)
    {
         String listData = "";
         DefaultListModel  dataList = new DefaultListModel();
         for (Item i : BorrowedItems)
         {
             
             listData = i.getId() + ", " + i.getTitle() + ", " + i.getItemType();
             dataList.addElement(listData);
         }
         
         //Display all resources
         jBorrowedItemsList.setModel(dataList);
         
    }
    
    //Displays Avaliability info of selected Resource
     public void updateAvaliabilityDisplay(JList jResourceList, JButton jBorrowItem, JTextArea jResourceCheckTextbox)
    {
        if (jResourceList.getSelectedValue() == null) {
            return;
        }
        
        
        jBorrowItem.setEnabled(false);
        String infoText = "";        
        String[] info = jResourceList.getSelectedValue().toString().split(", ");
        
        for (Item i : Items)
        {
            if (Integer.parseInt(info[0]) == i.getId())
            {
                selectedItem = i;
                
                infoText+= i.getTitle() + "\n";
                
                if (i.getBorrowInfo().getIsBorrowed() == false) {
                    infoText += "*In Stock*";
                    jBorrowItem.setEnabled(true);
                    
                }
                else
                {
                    infoText += "*Not in Stock*\nDue to be returned on " + i.getBorrowInfo().getReturnDate(); 
                }
                
                if (i.getRating().getUserRatingList().isEmpty() == true)
                {
                    infoText += "\n\nUser Rating: 0 (No Ratings Yet)";
                }
                else
                {
                    infoText += "\n\nUser Rating: " + i.getRating().getAverageScore() + " (" + i.getRating().getTotalUsersThatRated() + ")";
                }
                
                if (i.getRating().searchRatingById(c.getId()) == 0) {
                    infoText += "\nYour rating: 0 (You have not rated this item yet)";
                }
                else
                {
                    infoText += "\nYour rating: " + i.getRating().searchRatingById(c.getId());
                }
            }
        }
        
        jResourceCheckTextbox.setText(infoText);
        jResourceCheckTextbox.setCaretPosition(0);
    }
     
     //Displays status info of selected borrowed Resource
     public void updateStatusDisplay(JList jBorrowedItemsList, JButton jReturnItem, JButton jExtensionButton, JButton jGiveRating, JTextField jPaymentField, JTextArea jStatusTextbox)
     {
        jReturnItem.setEnabled(false);
        jExtensionButton.setEnabled(false);
        jGiveRating.setEnabled(false);
        jPaymentField.setEnabled(false);
         
        String infoText = "";        
        
        if (jBorrowedItemsList.getSelectedValue() != null) {
            String[] info = jBorrowedItemsList.getSelectedValue().toString().split(", ");
            
            for (Item i : BorrowedItems)
            {
                if (Integer.parseInt(info[0]) == i.getId())
                {
                    selectedBorrowedItem = i;
                    infoText += selectedBorrowedItem.getTitle();
                    infoText += "\nBorrowed on: " + i.getBorrowInfo().getStartDate();
                    infoText += "\nReturn Date: " + i.getBorrowInfo().getReturnDate();
                    jReturnItem.setEnabled(true);
                    jExtensionButton.setEnabled(true);
                    jGiveRating.setEnabled(true);
                
                    infoText += "\n\nIs Item Overdue?: ";
                    if (i.getBorrowInfo().getIsOverdue() == true)
                    {
                        jPaymentField.setEnabled(true);
                        
                        
                        infoText += "\n\nItem is Overdue! Please return as soon as possible"; 
                        infoText += "\nOverdue Amount Owned: " + i.getBorrowInfo().returnOverdueAmountString();
                    }
                    else
                    {
                        infoText += " No";
                    }
                
                    infoText += "\nCurrent Extensions: " + i.getBorrowInfo().getExtension();
                }
            }
        }
        
        jStatusTextbox.setText(infoText);
        jStatusTextbox.setCaretPosition(0);
     }
     
     //Displays all messages to user
     public void loadMessages(JTextArea jMessageDisplayTextbox)
    {
         
        
         String messageData = "";
         String divider = "\n\n****\n\n";
         
         
         for (Message m : c.getMessages())
         {
            
            messageData += m.getMessageSubject() + "\n" + m.getMessageBody() + "\n\n" + m.getSentDateTime();
            messageData += divider;
            
         }
         
         //Display all resources
         jMessageDisplayTextbox.setText(messageData);
    }
     
     //Lets user borrow resource
    public void borrowItem(JRadioButton jBorrowLength1, JList jResourceList, JButton jBorrowItem, JTextArea jResourceCheckTextbox)
    {
         int borrowLength;
        
        if (jBorrowLength1.isSelected() == true)
        {
            borrowLength = 0;
        }
        else
        {
            borrowLength = 1;
        }
        
        System.out.println(c.getId());
        selectedItem.borrowItem(c, borrowLength);
        
       
        jBorrowItem.setEnabled(false);
    }
          
    //Lets user return resource
     public void returnItem(JButton jReturnItem, JButton jExtensionButton, JButton jGiveRating, JTextField jPaymentField)
     {
         if (selectedBorrowedItem.getBorrowInfo().getIsOverdue() == false) {
            selectedBorrowedItem.returnItem();
            
            jReturnItem.setEnabled(false);
            jExtensionButton.setEnabled(false);
            jGiveRating.setEnabled(false);
            JOptionPane.showMessageDialog(cw, "Successfully Returned :)");
        }
        else
        {
        
            if (selectedBorrowedItem.getBorrowInfo().getIsOverdue() == true) {
                
                if (jPaymentField.getText().equals(""))
                {
                    jPaymentField.setText("0.00");
                }
                
                int amount = (int) (Float.parseFloat(jPaymentField.getText()) * 100);
                selectedBorrowedItem.getBorrowInfo().payOverdueAmount(amount);
                if (selectedBorrowedItem.getBorrowInfo().getOverdueAmount() == 0)
                {
                    
                    selectedBorrowedItem.returnItem();
                    updateBorrowedItems();
                    jReturnItem.setEnabled(false);
                    jExtensionButton.setEnabled(false);
                    jGiveRating.setEnabled(false);
                    jPaymentField.setEnabled(false);
                    jPaymentField.setText("0.00");
                }
                else
                {
                JOptionPane.showMessageDialog(cw, "Please pay overdue amount!");
                }
            }
            
        }
     }
     
     //Lets user request Extension
     public void requestExtension(JRadioButton j3DayExtension, JRadioButton j1WeekExtension)
     {
         int extension;
        
        if (selectedBorrowedItem.getBorrowInfo().getIsOverdue() == true) {
            JOptionPane.showMessageDialog(cw, "Cannot request extension! Item is overdue, please return.");
        }
        else
        {
            if (selectedBorrowedItem.getBorrowInfo().getIsExtensionPending() == false) {
                
            
                if (j3DayExtension.isSelected() == true) {
                    extension = 3;
                }
                else if (j1WeekExtension.isSelected() == true)
                {
                    extension = 7;
                }
                else
                {
                    extension = 14;
                }
            
                selectedBorrowedItem.getBorrowInfo().requestExtension(extension, adminMessages);
                JOptionPane.showMessageDialog(cw, "Extension request sent!");
                return;
            }
            else if (selectedBorrowedItem.getBorrowInfo().getIsExtensionPending() == true)
            {
                JOptionPane.showMessageDialog(cw, "Extension is pending.");
            }
            else if (selectedBorrowedItem.getBorrowInfo().getExtension() > 0);
            {
                JOptionPane.showMessageDialog(cw, "There is already an extension on this item!");
            }
            
        }
     }
     
     //Lets user rate a resource
     public void giveRating(JRadioButton jRating1, JRadioButton jRating2, JRadioButton jRating3, JRadioButton jRating4, JRadioButton jRating5)
     {
         if (jRating1.isSelected() == false && jRating2.isSelected() == false && jRating3.isSelected() == false && jRating4.isSelected() == false && jRating5.isSelected() == false)
        {
            JOptionPane.showMessageDialog(cw, "Please select a rating");
        }
        else
        {
            if (jRating1.isSelected() == true)
            {
                selectedBorrowedItem.getRating().addUserRating(c.getId(), 1);
            }
            else if (jRating2.isSelected() == true)
            {
                selectedBorrowedItem.getRating().addUserRating(c.getId(), 2);
            }
            else if (jRating3.isSelected() == true)
            {
                selectedBorrowedItem.getRating().addUserRating(c.getId(), 3);
            }
            else if (jRating4.isSelected() == true)
            {
                selectedBorrowedItem.getRating().addUserRating(c.getId(), 4);
            }
            else if (jRating5.isSelected() == true)
            {
                selectedBorrowedItem.getRating().addUserRating(c.getId(), 5);
            }
            
            JOptionPane.showMessageDialog(cw, "Rating Updated!");
            
            
            
        }
     }
     
     //Lets user request a new resource
     public void requestResource(JTextArea jResourceRequestTextbox)
     {
         Message temp;
        
        temp = new Message(c.getId(), "ADMIN", "Resource Request", jResourceRequestTextbox.getText(), adminMessages);
        
        adminMessages.add(temp);
        
        JOptionPane.showMessageDialog(cw, "Resource Request Sent");
        jResourceRequestTextbox.setText("");
     }
     
     //Logs user out
     public void logout()
     {
        cw.setVisible(false);
        logwin.setVisible(true);
        logwin.saveInformation();
     }
    
  

    
}
