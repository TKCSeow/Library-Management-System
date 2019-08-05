/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel.User;

import LibraryModel.Message;
import java.util.ArrayList;
import Controller.IObserver;
import LibraryModel.Newsletter;


/**
 *
 * @author tseow
 */
public class Client extends User implements IObserver{

    private ArrayList<Message> Messages;
    
    private Boolean isNewsletterRead;
    
    
    public Client(String id, String password, String firstName, String lastName) {
        super(id, password, firstName, lastName);
        Messages = new ArrayList<>();
        isNewsletterRead = false;
        Newsletter.getInstance().registerObserver(this);
    }
  
    //private List<Items> borrowedItems

    public ArrayList<Message> getMessages() {
        return Messages;
    }

    public void setMessages(ArrayList<Message> Messages) {
        this.Messages = Messages;
    }

    public Boolean getIsNewsletterRead() {
        return isNewsletterRead;
    }

    public void setIsNewsletterRead(Boolean isNewsletterRead) {
        this.isNewsletterRead = isNewsletterRead;
    }
    
        @Override
    public UserType getUserType() {
        return UserType.CLIENT;
    }

    @Override
    public void update() {
        isNewsletterRead = false;
    }


    

   

 
    
}
