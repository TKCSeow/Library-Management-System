/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import Controller.IObserver;

/**
 * The Observable interface. 
 * @author arahat
 */
public interface IObservable {
 
    Boolean registerObserver(IObserver o);  
    Boolean removeObserver(IObserver o);
    void notifyObservers();


}

