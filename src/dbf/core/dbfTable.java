/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbf.core;

import dbf.data.DataRecord;
import dbf.data.FieldData;
import dbf.events.dbfListener;
import dbf.events.dbfProgressChangedEvent;
import dbf.headers.DBFHeader;
import dbf.headers.FieldDataException;
import dbf.headers.FieldHeader;
import dbf.headers.RecordHeader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import struct.JavaStruct;
import struct.StructException;

/**
 *
 * @author Aleksey
 */
public final class dbfTable {
     private final Map<Integer, String> hm;
     private final DBFHeader FileHeader;
     private int HeaderSize;
     private final FileChannel fs;
     private final List<FieldData> fields;
     private short RecordLenght;
     private final List<DataRecord> records;
     private final long elapsedTime;
     private final List<dbfListener> listeners;
     static int pre_progress = 0;
    
     public dbfTable(FileInputStream fs) throws IOException, StructException, FieldDataException {
        
            this.fs = fs.getChannel();              
            hm = new HashMap<>();
            records = new  ArrayList<>();
            fields  = new  ArrayList<>();
            listeners = new  ArrayList<>();
            FileHeader =  new DBFHeader(); 
            
            FillFileTypeSingnatures();            
            ReadDBFHeaders();
            FillFieldsData();
            
            int recCount = getRecordsCount();
            
            long startTime = System.currentTimeMillis();
            
//            this.addListener((dbfProgressChangedEvent e) -> {
//                System.out.println("Progress:"+e.getProgressPercentage());
//            });
            
            for(int i=0;i<recCount; i++){                
                LoadRecord(i);                
            }
              long stopTime = System.currentTimeMillis();
              elapsedTime = stopTime - startTime;
              
              
    }
          
     public dbfTable(FileInputStream fs, dbfListener loaderListener) throws IOException, StructException, FieldDataException{
        this.fs = fs.getChannel();              
            hm = new HashMap<>();
            records = new  ArrayList<>();
            fields  = new  ArrayList<>();
            listeners = new  ArrayList<>();
            FileHeader =  new DBFHeader(); 
            
            FillFileTypeSingnatures();            
            ReadDBFHeaders();
            FillFieldsData();
            
            int recCount = getRecordsCount();
            
            long startTime = System.currentTimeMillis();
            
            this.addListener(loaderListener);
            
            for(int i=0;i<recCount; i++){                
                LoadRecord(i);                
            }
              long stopTime = System.currentTimeMillis();
              elapsedTime = stopTime - startTime;
      }       
     
     private void FillFileTypeSingnatures(){
            hm.put(0x02 ,  "FoxBASE");
            hm.put(0x03 ,  "FoxBASE+/Dbase III plus, no memo");
            hm.put(0x30 ,  "Visual FoxPro");
            hm.put(0x31 ,  "Visual FoxPro, autoincrement enabled");
            hm.put(0x32 ,  "Visual FoxPro with field type Varchar or Varbinary");
            hm.put(0x43 ,  "dBASE IV SQL table files, no memo");
            hm.put(0x63 ,  "dBASE IV SQL system files, no memo");
            hm.put(0x83 ,  "FoxBASE+/dBASE III PLUS, with memo");
            hm.put(0x8B ,  "dBASE IV with memo");
            hm.put(0xCB ,  "dBASE IV SQL table files, with memo");
            hm.put(0xF5 ,  "FoxPro 2.x (or earlier) with memo");
            hm.put(0xE5 ,  "HiPer-Six format with SMT memo file");
            hm.put(0xFB ,  "FoxBASE");
     }
     
     private void ReadDBFHeaders() throws IOException, StructException{
           
            ByteBuffer buffer = ByteBuffer.allocate(32);
            fs.read(buffer);          
            JavaStruct.unpack(FileHeader,buffer.array());   
            HeaderSize = Utils.changeEndianness(ByteBuffer.wrap(FileHeader.LengthOfHeader).getShort());
            RecordLenght = Utils.changeEndianness(ByteBuffer.wrap(FileHeader.RecordDataLength).getShort());
    }
    
     private void FillFieldsData() throws IOException, StructException, FieldDataException{
        
          int fieldCount = getFieldCount();
              
          for(int i =0 ; i < fieldCount; i ++){
             ByteBuffer buffer = ByteBuffer.allocate(32);
                 int offset = 32 * (i+1);       
                 
               fs.position(offset);
               fs.read(buffer);
               FieldHeader field = new FieldHeader();
               JavaStruct.unpack(field,buffer.array());
               getFields().add(new FieldData(field));
          }
         
    
    }
     
     private void LoadRecord(int recordID) throws IOException, StructException {
                    
          
          
          FileChannel fileCh = fs;
          fileCh.position(HeaderSize + (RecordLenght*recordID));              
          ByteBuffer bbb = ByteBuffer.allocate(RecordLenght+1);              
          fileCh.read(bbb);        
          RecordHeader rh = new RecordHeader(RecordLenght);
          JavaStruct.unpack(rh, bbb.array());          
          getRecords().add(new DataRecord(rh, fields));
          
          if(listeners!=null){
              listeners.stream().filter((listener) -> (listener!=null)).forEach((listener) -> {
                  
                  int progress = recordID/(this.getRecordsCount()/100);
                  
                  if(progress>pre_progress){
                  dbfProgressChangedEvent event = new dbfProgressChangedEvent(this,progress);
                  listener.dbfProgressChanged(event);
                   pre_progress = progress;
                  }
                  if(progress == 100) pre_progress = 0;
              });
          }
          
     }
     
     private int getFieldCount( ){
       return   (Utils.changeEndianness(ByteBuffer.wrap(FileHeader.LengthOfHeader).getShort())- 33)/32;
    }

    /**
     * @return the fields
     */
    public List<FieldData> getFields() throws FieldDataException {
        if(fields == null) throw new FieldDataException("Fields Data not loaded!");
        return fields;
    }
    
    public int getRecordsCount(){
        
        return Utils.changeEndianness(FileHeader.NumberOfRecords);
    }

    /**
     * @return the records
     */
    public List<DataRecord> getRecords() {
        return records;
    }

    /**
     * @return the elapsedTime
     */
    public long getElapsedTime() {
        return elapsedTime;
    }
    
    public boolean addListener(dbfListener listener){
    
        if(listener!=null){
            return this.listeners.add(listener);
         } 
         return false;
    
    }

}
