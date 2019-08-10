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
public class AvailableState implements IState,Serializable{

    @Override
    public void borrowItem(Item o, Client c, int term) {
        
        //Loans Item
        o.getBorrowInfo().loanItemToUser(c.getId(), term);
        
        
        System.out.println("You are now borrowing");
        
        //Set new state
        o.setState(new BorrowedState());
    }

    @Override
    public void returnItem(Item o) {
         System.out.println("This item has not been borrowed yet.");
        o.setState(this);
    }

    
}
