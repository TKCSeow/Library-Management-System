/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel.Item;

/**
 *
 * @author tseow
 */
public class UserRating {
    private String userId;
    private int userScore;

    /**
     *
     * @param userId
     * @param userScore
     */
    public UserRating(String userId, int userScore) {
        this.userId = userId;
        this.userScore = userScore;
    }

    /**
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     */
    public int getUserScore() {
        return userScore;
    }

    /**
     *
     * @param userScore
     */
    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }
    
    
}
