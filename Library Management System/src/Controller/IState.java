/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import LibraryModel.Item;

/**
 *
 * @author tseow
 */
public interface IState {
    
    public void borrowItem(Item o);
    public void returnItem(Item o);
    public void processItem(Item o);
    public void printState();
    
    
}
