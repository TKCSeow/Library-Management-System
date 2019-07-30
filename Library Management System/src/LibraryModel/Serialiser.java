/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;
import java.io.*;
/**
 *
 * @author tseow
 */
public class Serialiser {
    
    private String fileName;

    public Serialiser(String name) {
        this.fileName = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String name) {
        this.fileName = name;
    }
    
    public Serializable readList(){
        Serializable tempList = null;
        try
        {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tempList = (Serializable) in.readObject();
            in.close();
            fileIn.close();
        } 
        catch (IOException i) 
        {
            System.out.println("File not found.");
            i.printStackTrace();
        } 
        catch (ClassNotFoundException c) 
        {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return tempList;
    }
    
    public boolean writeList (Serializable list)
    {
        try 
        {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in: " + fileName);
            return true;
        }   catch (IOException i) {
            System.out.println("Failed to load!");
            i.printStackTrace();
            return false;
        }
    }
}
