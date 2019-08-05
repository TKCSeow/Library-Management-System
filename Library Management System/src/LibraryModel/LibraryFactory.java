/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import LibraryModel.Item.State.AvailableState;
import Controller.IState;
import GuiView.LoginWindow;
import LibraryModel.Item.Book;
import LibraryModel.Item.DVD;
import LibraryModel.Item.Item;
import LibraryModel.Item.ItemType;
import LibraryModel.User.Admin;
import LibraryModel.User.Client;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author tseow
 */
public class LibraryFactory {

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
        
        
        
        
        
        public void openLibrary()
        {
            Admin a = new Admin( "a0", "",  "Alan",  "Jones");
        Admins.add(a);
        Client c = new Client( "c0", "",  "John",  "Smith");
        Clients.add(c);
        c = new Client( "c1", "cheese",  "Tom",  "Jerry");
        Clients.add(c);
        IState brandNew = new AvailableState();
        Book b = new Book( 100, "The Adventures of Tim");
        Items.add(b);
        
        b = new Book( 101, "The Misadventures of Tim");
        Items.add(b);
        
        b = new Book( 102, "The Tim of the World");
        Items.add(b);
        
        b = new Book( 200, "The Da Vintim Code");
        Items.add(b);
        
        DVD d = new DVD( 300, "The Da Vintim Code");
        Items.add(d);
        
            if (b.getItemType() == ItemType.BOOK) {
                System.out.println("It's a Book!");
            }
        
        
        Serialiser BookList = new Serialiser("BookList.ser");

        BookList.writeList(Items);
        
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);
        
        LoginWindow view = new LoginWindow(Admins, Clients, Items, adminMessages);
        view.setVisible(true);
        }
        
        
    
}
