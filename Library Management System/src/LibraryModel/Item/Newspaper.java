/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel.Item;

/**
 *
 * @author t_seo
 */
public class Newspaper extends Item {

    public Newspaper(int idNum, String name) {
        super(idNum, name);
    }

    @Override
    public ItemType getItemType() {
        return ItemType.NEWSPAPER;
    }
    
}
