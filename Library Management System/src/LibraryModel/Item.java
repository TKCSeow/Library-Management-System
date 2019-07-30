/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import Controller.IState;

/**
 *
 * @author tseow
 */
public abstract class Item {
    
    private int id;
    private String title;
    private int category;
    private Rating rating;
    private BorrowingInformation borrowInfo;
    private IState state;
    // private float userRating; //make an object?
    // Object for borrowing info: ClientInfo, BorrowLength, StartDate, ReturnDate, isOverdue, 
    
    
    public Item (int idNum, String name, int type, IState state)
    {
        id = idNum;
        title = name;
        category = type;
        rating = new Rating();
        this.state = state;
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

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

      
    public void setState(IState state) {
        this.state = state;
    }

    public IState getState() {
        return state;
    }
    
    public void borrowItem(Item o) {
        state.borrowItem(this);
    }

    public void returnItem(Item o) {
        state.returnItem(this);
    }
    
    
}