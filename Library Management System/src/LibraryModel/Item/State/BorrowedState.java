/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel.Item.State;

import LibraryModel.Item.Item;
import LibraryModel.User.Client;
import Controller.IState;
import java.io.Serializable;

/**
 *
 * @author tseow
 */
public class BorrowedState implements IState,Serializable{

    
    
    
    @Override
    public void borrowItem(Item o, Client c, int term) {
        System.out.println("Already Borrowed!");
        o.setState(this);
    }

    @Override
    public void returnItem(Item o) {
        o.getBorrowInfo().returnItem();
        o.setState(new AvailableState());
    }

    @Override
    public void processItem(Item o) {
        
    }

    @Override
    public void printState() {
        
    }

    @Override
    public void borrowItem(Item o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
