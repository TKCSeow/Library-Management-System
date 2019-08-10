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

    /**
     *
     * @param name
     */
    public Serialiser(String name) {
        this.fileName = name;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @param name
     */
    public void setFileName(String name) {
        this.fileName = name;
    }
    
    //Reads file and return it

    /**
     *
     * @return
     */
    public Serializable readFile(){
        Serializable tempFile = null;
        try
        {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tempFile = (Serializable) in.readObject();
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
        return tempFile;
    }
    
    //Write to file

    /**
     *
     * @param list
     * @return
     */
    public boolean writeFile (Serializable list)
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
