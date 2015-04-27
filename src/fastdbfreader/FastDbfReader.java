/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fastdbfreader;

import dbf.data.FieldData;
import dbf.headers.FieldDataException;
import dbf.core.dbfTable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import struct.StructException;

/**
 *
 * @author Aleksey
 */
public class FastDbfReader {

    /**
     * @param args the command line arguments
     */
    public   void Test(String [] args) throws FileNotFoundException, StructException, FieldDataException{
    
        try {
         
            String filePath = "E:\\kladr\\fias_dbf\\ADDROBJ.DBF";
            
            
            
           
            
            FileInputStream is = new FileInputStream(new File(filePath));
            int res = 0;
            
            
            
            
            dbfTable table = new dbfTable(is);
            List<FieldData> fd = table.getFields();
            
              System.out.println("Record count:"+table.getRecordsCount());
             System.out.println("Record load time:"+table.getElapsedTime()+" ms");
              
          for(FieldData field: fd){
            long startTime = System.currentTimeMillis();
            
            System.out.println("Field  Name:"+ field.getFiledName() + " Type:"+ field.getDbfTypeUtil().GetType());
            System.out.println("Field  Value:"+table.getRecords().get(0).getValue(field.getFiledName() ));
            System.out.println("Field  Value:"+table.getRecords().get(table.getRecordsCount()-1).getValue(field.getFiledName() ));
            long stopTime = System.currentTimeMillis();
            System.out.println("1 field rec get:"+(stopTime - startTime)+" ms");
            }
       
        } catch (IOException ex) {
            Logger.getLogger(FastDbfReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
   
}
