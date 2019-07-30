/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import java.io.Serializable;
import java.util.Observer;

/**
 *
 * @author tseow
 */
public abstract class User implements Observer,Serializable {
    private String id;
    private String password;
    private String firstName;
    private String lastName;

    public User(String id, String password, String firstName, String lastName) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
