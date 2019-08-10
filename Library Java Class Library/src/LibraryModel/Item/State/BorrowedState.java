/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel.Item.State;

import LibraryModel.Item.Item;
import LibraryModel.User.Client;
import Utilities.IState;
import java.io.Serializable;

/**
 *
 * @author tseow
 */
public class BorrowedState implements IState,Serializable{

    /**
     *
     * @param o
     * @param c
     * @param term
     */
    @Override
    public void borrowItem(Item o, Client c, int term) {
        System.out.println("Already Borrowed!");
        o.setState(this);
    }

    /**
     *
     * @param o
     */
    @Override
    public void returnItem(Item o) {
        
        //Returns Item
        o.getBorrowInfo().returnItem();
        //Set new state
        o.setState(new AvailableState());
    }

   
    
}
