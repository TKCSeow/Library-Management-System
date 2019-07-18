/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import java.util.ArrayList;
import Controller.IObserver;

/**
 *
 * @author tseow
 */
public class ConcreteLibrary implements IObservable {
    
    private ArrayList<IObserver> observers = new ArrayList<IObserver>();
    
       @Override
    public void registerObserver(IObserver observer) {
        observer.update(id);
        observers.add(observer);
    }

    /**
     * Removing observers. 
     * @param observer
     */
    @Override
    public void removeObserver(IObserver observer) {
        int idx = observers.indexOf(observer);
        if (idx > 0) observers.remove(idx);
    }

    /**
     * Notifying observers following an action.
     */
    @Override
    public void notifyObserver() {
        observers.forEach((observer) -> {
            observer.update(greetings, info);
        });
    
}
