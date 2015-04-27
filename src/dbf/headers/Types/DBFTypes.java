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
public class DBFTypes {
 
    public TypeConverter Converter;
    
    char _code;
    
    public DBFTypes(char code) {
        _code = code;
        Converter = new TypeConverter();
    }
    
    public dbfType GetType(){
           
        return  GetType(_code);
     
    }
    
    dbfType GetType(char typeCode){

           switch(typeCode){
           
               case 'C':  { return dbfType.CHARACTER; } 
               
               case 'N':  { return dbfType.NUMERIC; } 
               
               case 'D':  { return dbfType.DATE; } 
              
               case 'L':  { return dbfType.LOGICAL; } 
               
               case 'M':  { return dbfType.LOGICAL; } 
           }
        
         return null;
    }
     
    
    
    
}
