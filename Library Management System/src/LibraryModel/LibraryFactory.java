/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import Controller.LoginController;
import GuiView.LoginWindow;
import LibraryModel.Item.Book;
import LibraryModel.Item.DVD;
import LibraryModel.Item.Item;
import LibraryModel.Item.Magazine;
import LibraryModel.Item.Newspaper;
import LibraryModel.User.Admin;
import LibraryModel.User.Client;
import java.util.ArrayList;

/**
 *
 * @author tseow
 */
public class LibraryFactory {

        private LoginController logController;
        ArrayList<Admin> Admins;
        ArrayList<Client> Clients;
        ArrayList<Item> Items;
        ArrayList<Message> adminMessages;

    public LibraryFactory() {
        
        Admins = new ArrayList<>();
        Clients = new ArrayList<>();
        Items  = new ArrayList<>();
        adminMessages = new ArrayList<>();
        
    }
        
        
        
        
    //Open the library
    public void openLibrary()
    {
        //Uncomment the line below to reset the library to default data
        //resetToDefault();
        
        //Read in Data
        readFiles();
            
           
        checkOverdue();
        logController = new LoginController(Admins, Clients, Items, adminMessages);
        logController.openWindow();
    }
        
    //Runs the checkOverdue() method in each item
    private void checkOverdue()
    {
        for (Item i : Items)
        {
            if (i.getBorrowInfo().getIsBorrowed() == true) {
                
                for (Client c : Clients)
                {
                    if (i.getBorrowInfo().getUserID().equals(c.getId())) {
                        i.getBorrowInfo().checkOverdue(c);
                    }
                }
                
                
            }
            
        }
    }
    
    //Loads/Read data
    public void readFiles()
    {
        Serialiser BookList = new Serialiser("BookList.ser");
        Serialiser AdminList = new Serialiser("AdminList.ser");
        Serialiser ClientList = new Serialiser("ClientList.ser");
        Serialiser Messages = new Serialiser("Messages.ser");
        Serialiser NewsletterFile = new Serialiser("Newsletter.ser");
        
        Items = (ArrayList<Item>) BookList.readFile();
        Clients = (ArrayList<Client>) ClientList.readFile();
        Admins = (ArrayList<Admin>) AdminList.readFile();
        adminMessages = (ArrayList<Message>) Messages.readFile();
        NewsletterSingleton.getInstance().setInstance((NewsletterSingleton) NewsletterFile.readFile());
    }
    
    
    //Resets Data to default info/data
    private void resetToDefault()
    {
        Admins.clear();
        Clients.clear();
        Items.clear();
        adminMessages.clear();
        
        
        Admin a = new Admin( "A0", "admin",  "Alan",  "Jones");
        Admins.add(a);
        
        Client c = new Client( "C0", "pass",  "John",  "Smith");
        Clients.add(c);
        c = new Client( "C1", "cheese",  "Tom N.",  "Jerry");
        Clients.add(c);
        c = new Client( "C2", "yes",  "Simon",  "Sayes");
        Clients.add(c);
        c = new Client( "C3", "no",  "Bopis",  "Jonson");
        Clients.add(c);
        c = new Client( "C4", "johnsons",  "John",  "Johnsons");
        Clients.add(c);
        
        
        

        Book b = new Book( 1800, "The Adventures of Tim");
        Items.add(b);
        
        b = new Book( 2800, "The Misadventures of Tim");
        Items.add(b);
        
        b = new Book( 3800, "The Tim of the World");
        Items.add(b);
        
        b = new Book( 1000, "Coding with Tim");
        Items.add(b);
        
        b = new Book( 4800, "The Da Vintim Code");
        Items.add(b);
        
        b = new Book( 1500, "The Tiny World Around Us");
        Items.add(b);
        
        b = new Book( 2500, "This Factory Within Us");
        Items.add(b);
        
        b = new Book( 1200, "Religions Around the World with Tim");
        Items.add(b);
        
        b = new Book( 1300, "The 2008 Bubble by Tim");
        Items.add(b);
        
        b = new Book( 1400, "Easy Japanese with Tim");
        Items.add(b);
        
        b = new Book( 1200, "Tim's Vision of the World");
        Items.add(b);
        
        DVD d = new DVD( 5800, "The Da Vintim Code");
        Items.add(d);
        
        d = new DVD( 6800, "Dom & Gerry: The Great Adventure");
        Items.add(d);
        
        d = new DVD( 1600, "Tim's Big Ideas");
        Items.add(d);
        
        Newspaper n = new Newspaper( 1900, "The Daily Telegraph (04-02-2012)");
        Items.add(n);
        n = new Newspaper( 2900, "The Daily Telegraph (011-02-2012)");
        Items.add(n);
        n = new Newspaper( 3900, "The Daily Telegraph (18-02-2012)");
        Items.add(n);
        
        Magazine m = new Magazine( 1700, "Cooking With Tim: Issue 24 (23-03-2019)");
        Items.add(m);
        m = new Magazine( 2700, "Cars of the World: Issue 5 (09-04-2018)");
        Items.add(m);
        m = new Magazine( 3700, "Drawing With Tim: Issue 14 (17-09-2019)");
        Items.add(m);
        
        Serialiser BookList = new Serialiser("BookList.ser");
        Serialiser AdminList = new Serialiser("AdminList.ser");
        Serialiser ClientList = new Serialiser("ClientList.ser");
        Serialiser Messages = new Serialiser("Messages.ser");
        Serialiser NewsletterFile = new Serialiser("Newsletter.ser");
        
        BookList.writeFile(Items);
        AdminList.writeFile(Admins);
        ClientList.writeFile(Clients);
        Messages.writeFile(adminMessages);
        NewsletterFile.writeFile(NewsletterSingleton.getInstance());
    }
        
        
    
}
