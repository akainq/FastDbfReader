/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf.headers;

import struct.StructClass;
import struct.StructField;

/**
 *
 * @author Aleksey
 */
@StructClass
public class FieldHeader {
    
    @StructField(order = 0)
    public byte [] FieldName = new byte [11];
    
    @StructField(order = 1)
    public byte   FieldType;
    
    @StructField(order = 2)
    public byte [] FieldDataAddress = new byte [4];
   
    @StructField(order = 3)
    public byte  FieldLength;  
        
    @StructField(order = 4)
    public byte [] DecimalCount = new byte [1];

    @StructField(order = 5)
    public byte [] Reserv1 = new byte [2];
        
    @StructField(order = 6)
    public byte [] WorkAreaID = new byte [1];
    
    @StructField(order = 7)
    public byte [] SetFieldsFlag = new byte [1];    
    
    @StructField(order = 8)
    public byte [] Reserv2 = new byte [7];
    
    @StructField(order = 9)
    public byte [] IndexFieldFlag = new byte [1];
    
}
