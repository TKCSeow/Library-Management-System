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
    public Boolean registerObserver(IObserver o) {
        Boolean blnAdded = false;                   //Assume this method will fail
        //Validate that observer exists
        if (o != null) {
            //If observer list not initialised create it
            if (this.observers == null) {
                this.observers = new ArrayList<>();
            }
            //Add the observer to the list of registered observers if not already registered
            if(!this.observers.contains(o)){
                blnAdded = this.observers.add(o);
            }
        }
        //Return the result
        return blnAdded;    
    }

    @Override
    public Boolean removeObserver(IObserver o) {
        Boolean blnRemoved = false;
        //Validate we have something to remove
        if (o != null) {
            if(this.observers != null && this.observers.size() > 0){
                blnRemoved = this.observers.remove(o);
            }
        }
        return blnRemoved;
    }

    @Override
    public void notifyObservers() {
        if (this.observers != null && this.observers.size() > 0) {
            //Start foreach loop
            for (IObserver currentObserver : this.observers) {
                //Call update on this observer
                currentObserver.update();
            }
        }
    }
    
       
    
}
