/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author tseow
 */
public class Rating implements Serializable{
    
    private float averageScore;
    private ArrayList<UserRating> userRatingList;

    public Rating() {
        
        this.averageScore = 0f;
        this.userRatingList = new ArrayList<>();
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public ArrayList<UserRating> getUserRatingList() {
        return userRatingList;
    }
    
    public void addUserRating(String userID, int score)
    {
        UserRating temp;
        
        temp = new UserRating(userID, score);
        
        userRatingList.add(temp);
    }
    
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
    
}
