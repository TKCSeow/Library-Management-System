/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

/**
 *
 * @author tseow
 */
public class Items {
    
    private int id;
    private String title;
    private int category;
    private float userRating; //make an object?
    // Obect for borrowing info: ClientInfo, BorrowLength, StartDate, ReturnDate, isOverdue, 
    
    
    private Items (int idNum, String name, int type)
    {
        id = idNum;
        title = name;
        category = type;
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

    public float getUserRating() {
        return userRating;
    }

    public void setUserRating(float userRating) {
        this.userRating = userRating;
    }
    
    
    
}
