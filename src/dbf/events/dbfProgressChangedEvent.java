/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf.events;

import java.util.EventObject;

/**
 *
 * @author Aleksey
 */
public class dbfProgressChangedEvent extends EventObject{

    private int _ProgressPercentage;
    
    public dbfProgressChangedEvent(Object source, int ProgressPercentage) {
        super(source);
        _ProgressPercentage = ProgressPercentage;        
    }

    /**
     * @return the _ProgressPercentage
     */
    public int getProgressPercentage() {
        return _ProgressPercentage;
    }
    
}
