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
public class UserRating {
    private String userId;
    private int userScore;

    public UserRating(String userId, int userScore) {
        this.userId = userId;
        this.userScore = userScore;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
    
    
}
