/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel.Item;

import LibraryModel.Item.UserRating;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tseow
 */
public class Rating implements Serializable{
    
    private float averageScore;
    private ArrayList<UserRating> userRatingList; //Holds objects that hold a user id and their rating

    /**
     *
     */
    public Rating() {
        
        this.averageScore = 0f;
        this.userRatingList = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    public float getAverageScore() {
        return averageScore;
    }

    /**
     *
     * @param averageScore
     */
    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    /**
     *
     * @return
     */
    public ArrayList<UserRating> getUserRatingList() {
        return userRatingList;
    }
    
    //Add a user rating to list

    /**
     *
     * @param userID
     * @param score
     */
    public void addUserRating(String userID, int score)
    {
        
        //checks if user has already rated item and overwrites previous rating
        for (UserRating u : userRatingList)
        {
            if (userID.equals(u.getUserId()))
            {
                u.setUserScore(score);
                updateAverageScore();
                return; //if found, exit function
            }
        }
        
        //if it is a new user, add new user to list
        UserRating temp;
        
        temp = new UserRating(userID, score);
        
        userRatingList.add(temp);
        updateAverageScore();
    }
    
    /**
     *
     * @param userID
     * @param newScore
     */
    public void changeUserRating(String userID, int newScore)
    {
        
        for (UserRating u:userRatingList)
        {
            if (userID.equals(u.getUserId()))
            {
                u.setUserScore(newScore);
            }
        }
        
        updateAverageScore();
    }
    
    private void updateAverageScore()
    {
        int total = 0;
        float newAverageScore = 0;
        
        for (UserRating u:userRatingList)
        {
            total += u.getUserScore();
        }
        
        newAverageScore = (float) total / userRatingList.size();
        
        averageScore = newAverageScore;
    }
    
    /**
     *
     * @return
     */
    public int getTotalUsersThatRated()
    {
        return userRatingList.size();
    }
    
    /**
     *
     * @param id
     * @return
     */
    public int searchRatingById(String id)
    {
        
        
        for (UserRating u : userRatingList)
        {
            if (id.equals(u.getUserId()))
            {
                return u.getUserScore();
            }
        }
        
        return 0;
    }
    
}
