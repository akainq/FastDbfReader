/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf.events;

import java.util.EventListener;

/**
 *
 * @author Aleksey
 */
public interface  dbfListener extends EventListener {
    
    public void dbfProgressChanged(dbfProgressChangedEvent e);
    
}
