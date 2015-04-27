/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf.headers.Types;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aleksey
 */
public class TypeConverter implements Converter{

    /**
     *
     * @param mutableData
     * @param type
     * @return
     */
    @Override
    public Object Convert(Object mutableData, dbfType type) {
        
        Object ret = new Object();
          switch(type){
           
               case CHARACTER : { ret =  convertToString(mutableData); } break;
               
               case NUMERIC:  { ret =  convertToDouble(mutableData);  } break;
               
               case DATE:  { ret =  convertToDate(mutableData); } break;
              
               case LOGICAL:  { ret =  convertToBoolean(mutableData); } break;
               
               case MEMO:  {   ret =  convertToString(mutableData);} break;
           }
                
          return ret;
    }
    
    public String ToString(Object mutableData){    
            return (String)Convert(mutableData,dbfType.CHARACTER);        
    }

    public Double ToDouble(Object mutableData){    
            return (Double)Convert(mutableData,dbfType.NUMERIC);        
    }
    
    public Date ToDate(Object mutableData){    
            return (Date)Convert(mutableData,dbfType.DATE);        
    }
    
    
    private String convertToString(Object mutableData)  {
    try{
        if(mutableData!=null){
        byte[] buff = (byte[])mutableData;
            
          return new String(buff,"cp866");
        }
        return null; 
    }catch(UnsupportedEncodingException ex){
        Logger.getLogger(TypeConverter.class.getName()).log(Level.SEVERE, null, ex);
    }
        return null;
    }

    private Double convertToDouble(Object mutableData)  {
       
       if(mutableData!=null){
           try {
               byte[] buff = (byte[])mutableData;
               
               return  Double.parseDouble(new String(buff,"cp866"));
           } catch (UnsupportedEncodingException ex) {
               Logger.getLogger(TypeConverter.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        return null;
    }

    private Date convertToDate(Object mutableData) {
         Date ret = new Date();
        try{
      
               if(mutableData!=null){
        byte[] buff = (byte[])mutableData;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String  strToParse = new String(buff,"cp866").trim();
            if(strToParse==null || strToParse.isEmpty()){
             return null;
            }
            ret = formatter.parse(strToParse);
        }
        return ret;
        }catch(ParseException| UnsupportedEncodingException ex){
           Logger.getLogger(TypeConverter.class.getName()).log(Level.SEVERE, "Date Conversion Error", ex);
        }
        return null;
    }

    private Boolean convertToBoolean(Object mutableData) {
         byte[] buff = (byte[])mutableData;
         
        try {
            return Boolean.parseBoolean(new String(buff,"cp866"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TypeConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
    
    
    
    
}
