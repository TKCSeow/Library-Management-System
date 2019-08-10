/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Utilities.IObserver;

/**
 * The Observable interface. 
 * @author arahat
 */
public interface IObservable {
 
    /**
     *
     * @param o
     */
    void registerObserver(IObserver o);  

    /**
     *
     * @param o
     */
    void removeObserver(IObserver o);

    /**
     *
     */
    void notifyObservers();


}

