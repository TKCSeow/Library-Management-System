/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel.Item;

import LibraryModel.User.Client;
import Controller.IState;
import LibraryModel.Item.State.AvailableState;
import java.io.Serializable;

/**
 *
 * @author tseow
 */
public abstract class Item implements Serializable{
    
    private int id;
    private String title;
    private Rating rating;
    private BorrowingInformation borrowInfo;
    private IState state;
    
    // private float userRating; //make an object?
    // Object for borrowing info: ClientInfo, BorrowLength, StartDate, ReturnDate, isOverdue, 
    
    
    public Item (int idNum, String name)
    {
        id = idNum;
        title = name;
        rating = new Rating();
        state = new AvailableState();
        borrowInfo = new BorrowingInformation(this);
    }

    public Rating getScore() {
        return rating;
    }

    public void setScore(Rating score) {
        this.rating = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

      
    public void setState(IState state) {
        this.state = state;
    }

    public IState getState() {
        return state;
    }
    
    public void borrowItem(Client c, int term) {
        state.borrowItem(this, c, term);
    }

    public void returnItem(Item o) {
        state.returnItem(this);
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public BorrowingInformation getBorrowInfo() {
        return borrowInfo;
    }

    public void setBorrowInfo(BorrowingInformation borrowInfo) {
        this.borrowInfo = borrowInfo;
    }
    
    public abstract ItemType getItemType();

    
    
}
