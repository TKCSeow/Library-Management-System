/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel.Item;

import Utilities.IState;

/**
 *
 * @author tseow
 */
public class Book extends Item{
    
    public Book (int id, String title)
    {
        super(id, title);
    }

    @Override
    public ItemType getItemType() {
        return ItemType.BOOK;
    }
    
}
