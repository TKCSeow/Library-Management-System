/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel.Item;

import LibraryModel.Item.Item;
import LibraryModel.Message;
import LibraryModel.User.Client;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author tseow
 */
public class BorrowingInformation  implements Serializable{
    
    private Item item;
    private Boolean isBorrowed;
    private String userID;
    private int borrowLength;
    private LocalDate startDate;
    private LocalDate returnDate;
    private Boolean isOverdue;
    private int extension;
    private int overdueAmount;
    private Boolean isExtensionPending;
    private int pendingExtension;
    private int reminderFrequency;
    private LocalDate lastReminder;

    public BorrowingInformation(Item i) 
    {
        item = i;
        this.isBorrowed = false;
        this.userID = null;
        this.isOverdue = false;
        this.extension = 0;
        this.overdueAmount = 0;
        this.isExtensionPending = false;
        this.pendingExtension = 0;
        this.reminderFrequency = 0;
        this.lastReminder = null;
    }

    public Boolean getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(Boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userId) {
        this.userID = userId;
    }

    public int getBorrowLength() {
        return borrowLength;
    }

    public void setBorrowLength(int borrowLength) {
        this.borrowLength = borrowLength;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate ReturnDate) {
        this.returnDate = ReturnDate;
    }

    public Boolean getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(Boolean isOverdue) {
        this.isOverdue = isOverdue;
    }

    public float getOverdueAmount() {
        return overdueAmount;
    }

    public void setOverdueAmount(int overdueAmount) {
        this.overdueAmount = overdueAmount;
    }

    public int getExtension() {
        return extension;
    }

    public void setExtension(int extension) {
        this.extension = extension;
    }

    public Boolean getIsExtensionPending() {
        return isExtensionPending;
    }

    public void setIsExtensionPending(Boolean isExtensionPending) {
        this.isExtensionPending = isExtensionPending;
    }

    public int getReminderFrequency() {
        return reminderFrequency;
    }

    public void setReminderFrequency(int reminderFrequency) {
        this.reminderFrequency = reminderFrequency;
    }
    
    
    //Lends resource to user
    public void loanItemToUser(String userId, int borrowPeriod)
    {
        int daysBorrowing;
        
        this.isBorrowed = true;
        this.userID = userId;
        this.startDate = LocalDate.now();
        
        if (borrowPeriod == 0)
        {
            daysBorrowing = 14;
        }
        else
        {
            daysBorrowing = 31 * 6;
        }
        
        this.returnDate = startDate.plusDays(daysBorrowing);
        this.isOverdue = false;
        this.extension = 0;
        
        System.out.println(startDate);
        System.out.println(userID);
        
    }
    
    //Check if resource is overdue
    public void checkOverdue(Client c)
    {
        LocalDate currentDate = LocalDate.now();
        
        //Add extension to return date
        LocalDate returnDateFull = returnDate.plusDays(extension);
        
       // LocalDate fakeDate = currentDate.plusDays(20);
        
        
        if (returnDateFull.isBefore(currentDate))
        {
            isOverdue = true;
            
            int i = returnDateFull.compareTo(currentDate) ;
            
            //Calculates overdue payment amount
            overdueAmount =  Math.abs(i) * 10;

        }
        
        //Sends Reminder if automated reminder is set
        if (reminderFrequency > 0){
            
            if (currentDate.isAfter(lastReminder.plusDays(reminderFrequency - 1))) {
                sendReminder(c);
            }
        }
    }
    
    //Returns how manys days overdue the resource is
    public int returnDaysOverdue()
    {
        LocalDate currentDate = LocalDate.now();
        LocalDate returnDateFull = returnDate.plusDays(extension);
        
        int overdue = returnDateFull.compareTo(currentDate);
        return overdue;
    }
    
    //Formats overdueAmount to a string, £0.00
    public String returnOverdueAmountString()
    {
       int pound;
       int pence;
       String amount = "";
                        
       pound = (int) overdueAmount / 100;
       pence = (int) overdueAmount - (pound * 100);
       
       amount = "£" + pound + "." + pence;
       
       return amount; 
    }
    
    //Pay the overdue amount
    public boolean payOverdueAmount(int payment)
    {
        System.out.println(overdueAmount);
        System.out.println(payment);
        if (payment == overdueAmount)
        {
            overdueAmount = 0;
            System.out.println(overdueAmount);
            return true;
        }
        
        
        return false;
    }
    
    //Sets all info on resource to default
    public void returnItem()
    {
        
        if (overdueAmount > 0)
        {
            System.out.println("Item has pending payments, cannot be returned");
        }
        else
        {
            isBorrowed = false;
            userID = null;
            startDate = null;
            returnDate = null;
            isOverdue = false;
            extension = 0; 
            
            isExtensionPending = false;
            pendingExtension = 0;
            
            System.out.println("Item has been returned");
        }
    }
    
    //Sends a extension request
    public void requestExtension (int days, ArrayList<Message> adminMessages)
    {
        isExtensionPending = true;
        pendingExtension = days;
        
        
        Message temp;
        String messageSubject = "Extension Request";
        String messageBody = "This user has requested an extension of " + days + " days";
        messageBody += " for \"" + item.getTitle() + "\"";
        
        System.out.println(item.getBorrowInfo().getUserID());
        temp = new Message(item.getBorrowInfo().getUserID(), "ADMIN", messageSubject, messageBody, item.getId());
        System.out.println(temp.getMessageId());
        adminMessages.add(temp);
        
        System.out.println("Extension Requested Sent");
    }
    
    //Grant a extension
    public void grantExtension (boolean isGranted, Client c)
    {
        String messageSubject = "Your Extension Request";
        String messageBody = "Your request for an extension of " + pendingExtension + " days";
        messageBody += " for \"" + item.getTitle() + "\"";
        
        if (isGranted == true)
        {
            extension += pendingExtension; 
            isExtensionPending = false;
            pendingExtension = 0;
            
            messageSubject = "Extension Approved";
            messageBody += " has been approved.";
            
            System.out.println("Extension Granted");
        }
        else
        {
            isExtensionPending = false;
            pendingExtension = 0;
            
            messageSubject = "Extension Denied";
            messageBody += " has been denied.";
            
            System.out.println("Extension Refused");
        }
        
        Message temp;
        
        temp = new Message("ADMIN", c.getId(), messageSubject, messageBody);
        c.getMessages().add(temp);
    }
    
    //Set Reminder
    public void setReminder(int frequency, Client c)
    {
        reminderFrequency = frequency;
        sendReminder(c);
    }
    
    //Sends reminder to client
    public void sendReminder(Client c)
    {
        String messageSubject = "Reminder";
        String messageBody;
        
        if (item.getBorrowInfo().isOverdue) {
            messageBody = "\"" + item.getTitle() + "\" is overdue, please return as soon as possible";
        }
        else
        {
            messageBody = "Please remember to return \"" + item.getTitle() + "\" on " + item.getBorrowInfo().returnDate;
        }
        
        Message temp;
        
        temp = new Message("ADMIN", c.getId(), messageSubject, messageBody);
        
        c.getMessages().add(temp);
        
        lastReminder = LocalDate.now();
        
        
    }
    
    //Cancel Reminder
    public void cancelReminders()
    {
        reminderFrequency = 0;
        lastReminder = null;
    }
    
}
