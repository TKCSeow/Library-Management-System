/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import Utilities.IObservable;
import Utilities.IObserver;
import GuiView.ClientWindow;
import LibraryModel.User.Client;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author tseow
 */
public class NewsletterSingleton implements IObservable, Serializable{
    private static NewsletterSingleton instance = null;
    
    private ArrayList<IObserver> observers;
    
    private String newsTitle = "No Title";
    private String newsBody = "No Text";
    private LocalDate publishDate = LocalDate.now();
    
    
    public static NewsletterSingleton getInstance()
    {
        if (instance == null)
        {
    	instance = new NewsletterSingleton(); 
        }
   	return instance;
    }  

    public NewsletterSingleton() {
        this.observers = new ArrayList<>();
                
    }
    
    
    public void updateNewsletter(String title, String body)
    {
        newsTitle = title;
        newsBody = body;
        publishDate = LocalDate.now();
        notifyObservers();           
    }
    
    public void displayNewsletter(JTextArea t)
    {
        String display = "";
        
        display += newsTitle + " (" + publishDate + ")" + "\n************\n";
        display += newsBody;
       
        
        t.setText(display);
        t.setCaretPosition(0);
    }
    
    public void checkIfNewsletterIsRead(Client c, ClientWindow cw)
     {
         if (c.getIsNewsletterRead() == false)
         {
             JOptionPane.showMessageDialog(cw, "The Newsletter has been updated. Check it out!");
             c.setIsNewsletterRead(true);
         }
     }
    
    
    
    public void setInstance(NewsletterSingleton nL)
    {
        instance = nL;
    }
    
    
    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        
        for (IObserver o : observers)
        {
            o.update();
        }
        
    }
    
}
