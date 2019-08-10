/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel.Item;

import LibraryModel.User.Client;
import Utilities.IState;
import LibraryModel.Item.State.AvailableState;
import java.io.Serializable;

/**
 *
 * @author tseow
 */
public abstract class Item implements Serializable{
    
    /**
     *
     */
    protected int id;

    /**
     *
     */
    protected String title;

    /**
     *
     */
    protected Rating rating;

    /**
     *
     */
    protected BorrowingInformation borrowInfo;

    /**
     *
     */
    protected IState state;
    
    // private float userRating; //make an object?
    // Object for borrowing info: ClientInfo, BorrowLength, StartDate, ReturnDate, isOverdue, 
    
    /**
     *
     * @param idNum
     * @param name
     */
    public Item (int idNum, String name)
    {
        id = idNum;
        title = name;
        rating = new Rating();
        state = new AvailableState();
        borrowInfo = new BorrowingInformation(this);
    }

    /**
     *
     * @return
     */
    public Rating getScore() {
        return rating;
    }

    /**
     *
     * @param score
     */
    public void setScore(Rating score) {
        this.rating = score;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @param state
     */
    public void setState(IState state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public IState getState() {
        return state;
    }
    
    //Borrows item using State Pattern

    /**
     *
     * @param c
     * @param term
     */
    public void borrowItem(Client c, int term) {
        state.borrowItem(this, c, term);
    }
    
    //Returns item using State Pattern

    /**
     *
     */
    public void returnItem() {
        state.returnItem(this);
    }

    /**
     *
     * @return
     */
    public Rating getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     */
    public BorrowingInformation getBorrowInfo() {
        return borrowInfo;
    }

    /**
     *
     * @param borrowInfo
     */
    public void setBorrowInfo(BorrowingInformation borrowInfo) {
        this.borrowInfo = borrowInfo;
    }
    
    /**
     *
     * @return
     */
    public abstract ItemType getItemType();

    
    
}
