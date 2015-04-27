/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf.headers.Types;

/**
 *
 * @author Aleksey
 */
interface Converter {
    
     Object Convert( Object mutableData, dbfType type);
    
    
}
