/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf.data;

import dbf.headers.FieldHeader;
import dbf.headers.Types.DBFTypes;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Aleksey
 */
public class FieldData {
    
    private FieldHeader _fHeader;

    private final String _filedName;
    
    private final DBFTypes dbfTypeUtil;
    
    private final Integer _fieldLenght;
    
    public FieldData(FieldHeader fHeader) throws UnsupportedEncodingException {
        
       if(fHeader!=null) _fHeader = fHeader;
       
        _filedName = new String(_fHeader.FieldName, "cp866");
                
        dbfTypeUtil = new DBFTypes((char)_fHeader.FieldType);
        
        _fieldLenght = Byte.toUnsignedInt(_fHeader.FieldLength);
    }

    /**
     * @return the _filedName
     */
    public String getFiledName() {
        return _filedName;
    }

    /**
     * @return the dbfTypeUtil
     */
    public DBFTypes getDbfTypeUtil() {
        return dbfTypeUtil;
    }

    /**
     * @return the _fieldLenght
     */
    public Integer getFieldLenght() {
        return _fieldLenght;
    }
    
    
    
}
