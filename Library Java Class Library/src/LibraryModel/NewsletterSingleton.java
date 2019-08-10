/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import Utilities.IObservable;
import Utilities.IObserver;

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
    
    /**
     *
     * @return
     */
    public static NewsletterSingleton getInstance()
    {
        if (instance == null)
        {
    	instance = new NewsletterSingleton(); 
        }
   	return instance;
    }  

    /**
     *
     */
    public NewsletterSingleton() {
        this.observers = new ArrayList<>();
                
    }
    
    /**
     *
     * @param title
     * @param body
     */
    public void updateNewsletter(String title, String body)
    {
        newsTitle = title;
        newsBody = body;
        publishDate = LocalDate.now();
        notifyObservers();           
    }
    
    /**
     *
     * @param t
     */
    public void displayNewsletter(JTextArea t)
    {
        String display = "";
        
        display += newsTitle + " (" + publishDate + ")" + "\n************\n";
        display += newsBody;
       
        
        t.setText(display);
        t.setCaretPosition(0);
    }
    
    /**
     *
     * @param c
     */
    public void checkIfNewsletterIsRead(Client c)
     {
         if (c.getIsNewsletterRead() == false)
         {            
             c.setIsNewsletterRead(true);
         }
     }
    
    /**
     *
     * @param nL
     */
    public void setInstance(NewsletterSingleton nL)
    {
        instance = nL;
    }
    
    /**
     *
     * @param o
     */
    @Override
    public void registerObserver(IObserver o) {
        observers.add(o);
    }

    /**
     *
     * @param o
     */
    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    /**
     *
     */
    @Override
    public void notifyObservers() {
        
        for (IObserver o : observers)
        {
            o.update();
        }
        
    }
    
}
