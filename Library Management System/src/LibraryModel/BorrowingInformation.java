/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

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

    public void setUserID(String userID) {
        this.userID = userID;
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
    }
    
    public void checkOverdue()
    {
        LocalDate currentDate = LocalDate.now();
        
        LocalDate fakeDate = currentDate.plusDays(20);
        System.out.println(fakeDate);
        
        
        LocalDate returnDateFull = returnDate.plusDays(extension);
        
        if (returnDateFull.isBefore(fakeDate))
        {
            isOverdue = true;
            
            int i = returnDateFull.compareTo(currentDate) ;
            
            overdueAmount =  Math.abs(i) * 10;

        }
    }
    
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
    
    public void requestExtension (int days, ArrayList<Message> adminMessages)
    {
        isExtensionPending = true;
        pendingExtension = days;
        
        
        Message temp;
        String messageSubject = "Extension Request";
        String messageBody = "This user has requested an extension of " + days + "days";
        messageBody += " for \"" + item.getTitle() + "\"";
        
        temp = new Message(this.getUserID(), "ADMIN", messageSubject, messageBody, item.getId());
        adminMessages.add(temp);
        
        System.out.println("Extension Requested Sent");
    }
    
    public void grantExtension (boolean isGranted, Client c)
    {
        String messageSubject = "Extension Request";
        String messageBody = "Your request for an extension of " + pendingExtension + "days";
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
    
    
}
