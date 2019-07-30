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
public class Available implements IState{

    @Override
    public void borrowItem(Item o) {
         System.out.println("You are now borrowing");
        o.setState(new Borrowed());
    }

    @Override
    public void returnItem(Item o) {
         System.out.println("You are now borrowing this item");
        o.setState(this);
    }

    @Override
    public void processItem(Item o) {
    }

    @Override
    public void printState() {
    }
    
}
