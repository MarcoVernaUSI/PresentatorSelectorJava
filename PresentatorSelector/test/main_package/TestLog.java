package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestLog {
    public static final String DefaultPath = "test/database_test.json";
    public Log _log;
    
    @Before
    public void SetUp(){
        //Create the file with a Bob Semple entry inside
        JSONObject entry = new JSONObject();
        entry.put("entry","Bob Semple absent in date 01/09/1939 00:00:00");
        JSONArray objectsList = new JSONArray();
        objectsList.add(entry);
        try (FileWriter file = new FileWriter(DefaultPath)) {
            file.write(objectsList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        _log = new Log(DefaultPath);
    }
    
    @After
    public void rollback(){
        File file = new File(DefaultPath);
        file.delete();
    }
    
    @Test
    public void addMultipleEntries() {
        
        _log.saveEntry("George Pearce", true);
        Date actualDate1= new Date();
        _log.saveEntry("Winston Churchill", true);
        Date actualDate2= new Date();
        
        assertEquals("Bob Semple absent in date 01/09/1939 00:00:00", _log.getEntry(0));
        assertEquals("George Pearce absent in date " + Log.DateFormat.format(actualDate1), _log.getEntry(1));
        assertEquals("Winston Churchill absent in date "+Log.DateFormat.format(actualDate2), _log.getEntry(2));
        assertEquals(3, _log.countEntries());
        
    }
    
    @Test
    public void saveEntryTrue() {
        
        _log.saveEntry("George Pearce", true);
        Date actualDate= new Date();
        
        assertEquals("George Pearce absent in date " + Log.DateFormat.format(actualDate), _log.getEntry(1));
        assertEquals(2, _log.countEntries());
    }
    

    @Test
    public void saveEntryFalse() {
        String before =_log.printLog();
        
        _log.saveEntry("George Pearce", false);
        
        assertEquals(before, _log.printLog());
        assertEquals(1, _log.countEntries());
    }
    
    @Test
    public void clearLog() {
        
        _log.clearLog();
        
        assertEquals(0,_log.countEntries());
    }
    
    @Test
    public void printLog() {
        
        String logPrint = _log.printLog();
        
        assertEquals("\nBob Semple absent in date 01/09/1939 00:00:00",logPrint);
    }
    

}
