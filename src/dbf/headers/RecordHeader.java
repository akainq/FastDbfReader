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
public class RecordHeader {
   @StructField(order = 0)
    public byte [] deleteFlag = new byte[1];
    
    @StructField(order = 1)
    public byte [] data;

    public RecordHeader(int  lenghtdata) {
        this.data = new byte[lenghtdata];
    }
    
    
}
