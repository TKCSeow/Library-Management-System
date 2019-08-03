/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author tseow
 */
public class Client extends User{

    private ArrayList<Message> Messages;
    
    public Client(String id, String password, String firstName, String lastName) {
        super(id, password, firstName, lastName);
        Messages = new ArrayList<>();
    }
  
    //private List<Items> borrowedItems

    public ArrayList<Message> getMessages() {
        return Messages;
    }

    public void setMessages(ArrayList<Message> Messages) {
        this.Messages = Messages;
    }



    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
}
