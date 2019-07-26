/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import java.time.LocalDate;

/**
 *
 * @author tseow
 */
public class BorrowingInformation {
    
    private Boolean isBorrowed;
    private String userID;
    private int borrowLength;
    private LocalDate startDate;
    private LocalDate returnDate;
    private Boolean isOverdue;
    private int extension;

    public BorrowingInformation() 
    {
        this.isBorrowed = false;
        this.userID = null;
        this.isOverdue = false;
        this.extension = 0;
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

    public int getExtention() {
        return extension;
    }

    public void setExtention(int extention) {
        this.extension = extention;
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
    
    public void returnItem()
    {
        if (isOverdue == true)
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
        }
    }
    
    
    
}
