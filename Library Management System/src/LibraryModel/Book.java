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
public class Book extends Item{
    
    public Book (int id, String title, int category, IState state)
    {
        super(id, title, category, state);
    }
    
}
