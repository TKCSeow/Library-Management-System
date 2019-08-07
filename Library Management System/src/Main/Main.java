/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Utilities.IState;
import GuiView.LoginWindow;
import LibraryModel.User.Admin;
import LibraryModel.Item.State.AvailableState;
import LibraryModel.Item.Book;
import LibraryModel.User.Client;
import LibraryModel.Item.Item;
import LibraryModel.LibraryFactory;
import LibraryModel.Message;
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
        
        //Load Library
        LibraryFactory library = new LibraryFactory();
        library.openLibrary();
        

    }
}
