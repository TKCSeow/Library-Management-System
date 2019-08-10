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
public class Magazine extends Item {

    /**
     *
     * @param idNum
     * @param name
     */
    public Magazine(int idNum, String name) {
        super(idNum, name);
    }

    /**
     *
     * @return
     */
    @Override
    public ItemType getItemType() {
        return ItemType.MAGAZINE;
    }
    
}
