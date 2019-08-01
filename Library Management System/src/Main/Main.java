/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.IState;
import Controller.LibraryController;
import GuiView.LoginWindow;
import LibraryModel.AvailableState;
import LibraryModel.Book;
import LibraryModel.Client;
import LibraryModel.Item;
import LibraryModel.Serialiser;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tseow
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LibraryController controller = new LibraryController();
        
        
        ArrayList<Client> Clients = new ArrayList<Client>();
        ArrayList<Item> Items = new ArrayList<Item>();
        
        Client c = new Client( "c0", "password",  "John",  "Smith");
        Clients.add(c);
        IState brandNew = new AvailableState();
        Book b = new Book( 100, "The Adventures of Tim",  1, brandNew);
        Items.add(b);
        
        b = new Book( 101, "The Misadventures of Tim",  1, brandNew);
        Items.add(b);
        
        b = new Book( 102, "The Tim of the World",  1, brandNew);
        Items.add(b);
        
        b = new Book( 200, "The Da Vintim Code",  1, brandNew);
        Items.add(b);
        
        
        Serialiser BookList = new Serialiser("BookList.ser");

        BookList.writeList(Items);
        
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);
        
        LoginWindow view = new LoginWindow(Clients, Items);
        view.setVisible(true);
        
        
        System.out.println("Do you want to add a user? (y/n)");
 
        
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        
        if (answer.equals("y"))
        {
            AddClient(Clients);
        }
        
        System.out.println("Hello");
        System.out.println("Welcome to Tim's Library");
        System.out.println("Please Login");
        System.out.println("Enter User ID:");
        
        String validId = validateID(Clients);
        Boolean isValidLogin = false;
        
        System.out.println("Enter User Password:");
        Boolean isPassCorrect = validatePassword(Clients, validId);
            
        if (isPassCorrect == true)
        {
            isValidLogin = true;
        }
        else
        {
            isValidLogin = false;
        }
        
        
        if (isValidLogin == true)
        {
            System.out.println("Login Sucessful!!!");
            System.out.println("Search for Book:");
            Book temp = SearchBookID(Books);
            if (temp != null)
            {
                System.out.println(temp.getTitle());
                
                System.out.println("Borrow book? y/n");
               

                answer = input.nextLine();
                if (answer.equals("y"))
                {
                    b.returnItem(b);
                    
                    b.borrowItem(b, c, 0);
                    
                    b.borrowItem(b, c, 0);
                    
                   
                    
                    System.out.println("Your return date is " + b.getBorrowInfo().getReturnDate());
                    
                    System.out.println("Return book? y/n");
                    answer = input.nextLine();
                    
                    if (answer.equals("y")) {
                    
                        
                        b.returnItem(b);
                        
                        
                       
                    }
                    
                }
                
                
            }
            else
            {
                System.out.println("Book not found");
            }
        }
        else
        {
            System.out.println("Login Failed :(");
        }
        
        
        
        
        //AddUser();
        
        
        
        
    }
    
    public static String validateID(ArrayList<Client> Clients)
    {
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        
        for(Client c : Clients)
        {
            if (c.getId().equals(answer))
            {
                return c.getId();
            }
        }
        return null;
    }
    
    public static Boolean validatePassword(ArrayList<Client> Clients, String id)
    {
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        
        for(Client c : Clients)
        {
            if (c.getId().equals(id))
            {
                if (c.getPassword().equals(answer))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static Book SearchBookID (ArrayList<Book> Books)
    {
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine();
        Book temp;
        for(Book b : Books)
        {
            
                if (b.getId() == Integer.parseInt(answer))
                {
                    temp = b;
                    return temp;
                }
            }
        
        return null;
        
    }
    
    public static void AddClient(ArrayList<Client> Clients)
    {
        Boolean isRunning = false;
        
        do {
            System.out.println("Enter First Name");
            Scanner input = new Scanner(System.in);
            String fname = input.nextLine();
            
            System.out.println("Enter Last Name");
            String lname = input.nextLine();
            
            System.out.println("Create a password");
            String pass = input.nextLine();
            
            String id = "c" + (Clients.size());
            
           Client temp = new Client( id, pass,  fname,  lname);
           Clients.add(temp);
        
            //System.out.println(temp.getId());
            //System.out.println(temp.getPassword());
            //System.out.println(temp.getFirstName());
            //System.out.println(temp.getLastName());
            
            isRunning = true;
        
        } while (isRunning == false);
    }
    
    public static void exitProgram()
    {
        
        
        System.out.println("Do you want to exit? (y/n)");
            
            Scanner input = new Scanner(System.in);
            String answer = input.nextLine();
            
            if (answer.equals("y")){
                System.exit(0);
            }
    }
    
}
