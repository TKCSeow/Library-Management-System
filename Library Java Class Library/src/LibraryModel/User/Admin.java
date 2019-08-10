/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel.User;



/**
 *
 * @author tseow
 */
public class Admin extends User{

    /**
     *
     * @param id
     * @param password
     * @param firstName
     * @param lastName
     */
    public Admin(String id, String password, String firstName, String lastName) {
        super(id, password, firstName, lastName);
    }

    /**
     *
     * @return
     */
    @Override
    public UserType getUserType() {
        return UserType.ADMIN;
    }


    
    
    }
    
    

