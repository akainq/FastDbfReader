/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf.data;

import dbf.headers.RecordHeader;
import java.nio.ByteBuffer;
import java.util.List;
 

/**
 *
 * @author Aleksey
 */

public class DataRecord {        
    
    RecordHeader _recordheader;
    List<FieldData>  _fields;

    public DataRecord( RecordHeader recordheader, List<FieldData> fields) {
        
        _recordheader = recordheader;
        _fields = fields;
    }
    
    public Object getValue(String FieldName){
    
       ByteBuffer bb = ByteBuffer.wrap(_recordheader.data);
       
       for(FieldData field:  _fields){
       
           byte [] buff= new byte [field.getFieldLenght()];
           bb.get(buff);
           if(field.getFiledName() == null ? FieldName == null : field.getFiledName().equals(FieldName)){
           
              return  field.getDbfTypeUtil().Converter.Convert(buff,  field.getDbfTypeUtil().GetType());
           }
           
       }
        return null;
       
    }
    
}
