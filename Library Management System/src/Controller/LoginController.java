/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import GuiView.AdminWindow;
import GuiView.LoginWindow;
import LibraryModel.Item.Item;
import LibraryModel.Message;
import LibraryModel.NewsletterSingleton;
import LibraryModel.Serialiser;
import LibraryModel.User.Admin;
import LibraryModel.User.Client;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author tseow
 */
public class LoginController {
    
    private LoginWindow lg;
    private ArrayList<Admin> Admins;
    private ArrayList<Client> Clients;
    private ArrayList<Item> Items;
    private ArrayList<Message> Messages;
    private ClientController cController;
    private AdminController aController;
    private AdminWindow adminView;
    
    public LoginController(ArrayList<Admin> admins, ArrayList<Client> users, ArrayList<Item> items, ArrayList<Message> messages) {
    
        lg = new LoginWindow(this);
        Clients = users;
        Items = items;
        Admins = admins;
        Messages = messages;
    
    }
    
    //Opens Login GUI
    public void openWindow()
    {
        lg.setVisible(true);
    }
    
    //Check if login details match a user and logs the in
    public void validateLogin(JTextField idTextBox, JPasswordField PasswordTextbox)
    {
        
         for(Admin a : Admins)
        {
            //Compare id
            if (a.getId().equals(idTextBox.getText().toUpperCase()))
            {
                //Compare passwords
                if (a.getPassword().equals(getUserPasswordInput(PasswordTextbox))) 
                {              
                    //JOptionPane.showMessageDialog(this, "Correct");
                    lg.setVisible(false);
                    idTextBox.setText("");
                    PasswordTextbox.setText("");
                    aController = new AdminController(a, Clients, Items, Messages, lg);
                    aController.openWindow();
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(lg, "Password is incorrect");
                    PasswordTextbox.setText("");
                    return;
                }         
            }
            
        }
        
        for(Client c : Clients)
        {
            //Compare id
            if (c.getId().equals(idTextBox.getText().toUpperCase()))
            {
                //Compare passwords
                if (c.getPassword().equals(getUserPasswordInput(PasswordTextbox))) 
                {              
                    //JOptionPane.showMessageDialog(this, "Correct");
                    lg.setVisible(false);
                    idTextBox.setText("");
                    PasswordTextbox.setText("");
                    cController = new ClientController(c, Items,Messages, lg);
                    cController.openWindow();
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(lg, "Password is incorrect");
                    PasswordTextbox.setText("");
                    return;
                }         
            }
            
        }
        
        JOptionPane.showMessageDialog(lg, "User does not exist");
        PasswordTextbox.setText("");
    }
    
    //Converts input (char[]) to String
      public String getUserPasswordInput(JPasswordField PasswordTextbox)
    {
        String passwordString = "";
        
        char[] pass = PasswordTextbox.getPassword();
        
        for (int i = 0; i < pass.length; i++) {
            
            passwordString += pass[i];
        }
        
        return passwordString;
    }
    
      //Save/write data
       public void saveInformation()
    {
        Serialiser BookList = new Serialiser("BookList.ser");
        Serialiser AdminList = new Serialiser("AdminList.ser");
        Serialiser ClientList = new Serialiser("ClientList.ser");
        Serialiser MessagesFile = new Serialiser("Messages.ser");
        Serialiser NewsletterFile = new Serialiser("Newsletter.ser");
        
        BookList.writeFile(Items);
        AdminList.writeFile(Admins);
        ClientList.writeFile(Clients);
        MessagesFile.writeFile(Messages);
        NewsletterFile.writeFile(NewsletterSingleton.getInstance());
    }
}






