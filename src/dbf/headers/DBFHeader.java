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
public class DBFHeader {
    
    @StructField(order = 0)
    public byte [] FileType = new byte [1];
    
    @StructField(order = 1)
    public byte [] DateofLastUpdate = new byte[3];
    
    @StructField(order = 2)
    public int NumberOfRecords;
    
    @StructField(order = 3)
    public byte [] LengthOfHeader  = new byte[2];
    
    @StructField(order = 4)
    public byte [] RecordDataLength  = new byte[2];
    
    @StructField(order = 5)
    public byte [] Reserved  = new byte[2];
    
    @StructField(order = 6)
    public byte [] IncomplitTransaction = new byte [1];
    
    @StructField(order = 7)
    public byte [] EncryptionFlag = new byte [1];  
    
    @StructField(order = 8)
    public byte [] FreeRecordThread  = new byte[4];
    
    @StructField(order = 9)
    public byte [] MultiUserReserved   = new byte[8];
    
    @StructField(order = 10)
    public byte [] MDXFlag = new byte [1];  
 
    @StructField(order = 11)
    public byte [] LanguageDriver = new byte [1];  
    
    @StructField(order = 12)
    public byte [] Reserved2  = new byte[2];
    

}
