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
public class Borrowed implements IState{

    
    
    
    @Override
    public void borrowItem(Item o) {
        System.out.println("Already Borrowed!");
        o.setState(this);
    }

    @Override
    public void returnItem(Item o) {
        System.out.println("Already Borrowed!");
        o.setState(new Available());
    }

    @Override
    public void processItem(Item o) {
        
    }

    @Override
    public void printState() {
        
    }
    
}
