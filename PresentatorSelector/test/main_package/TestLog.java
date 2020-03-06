package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main_package.builders.JsonDatabaseBuilder;

public class TestLog {
    public static final String Path = JsonDatabaseBuilder.DefaultPath;
    
    public JsonDatabaseBuilder _db;
    
    
    @Before
    public void setUp() {
        _db = new JsonDatabaseBuilder().ofLogEntries().writeFile();
    }
    
    @After
    public void rollback(){
        File jsonFile = new File(Path);
        jsonFile.delete();
    }
    
    @Test
    public void loadDatabase() {
        Log log = new Log(_db.build());
        
        log.loadDatabase();
        
        assertEquals("Bob Semple",log.getEntries().get(0).getSpeaker());
        assertEquals("01/09/1939 00:00:00", log.getEntries().get(0).getDate());
        assertEquals("George Pearce", log.getEntries().get(1).getSpeaker());
        assertEquals("01/11/1942 00:00:00", log.getEntries().get(1).getDate());
    }

    @Test
    public void dumpDatabase() {
        Log log = new Log(_db.build());
        log.saveEntry("Neville Chamberlain", true);
        log.saveEntry("Winston Churchill", true);
        
        log.dumpDatabase();
        
        List<JSONObject> readedDatabase = _db.readDb();

        assertEquals("Bob Semple", readedDatabase.get(0).get("name"));
        assertEquals("George Pearce", readedDatabase.get(1).get("name"));
        assertEquals("Neville Chamberlain", readedDatabase.get(2).get("name"));
        assertEquals("Winston Churchill", readedDatabase.get(3).get("name"));  
    }
    
    
    @Test
    public void saveEntryTrue() {
        Log log = new Log(_db.build());
        
        log.saveEntry("Neville Chamberlain", true);        

        List<JSONObject> readedDatabase = _db.readDb();
        
        assertEquals("George Pearce", readedDatabase.get(1).get("name"));
        assertEquals("Neville Chamberlain", readedDatabase.get(2).get("name"));
        assertEquals(3,log.getEntries().size());
    }
    

    @Test
    public void saveEntryFalse() {
        Log log = new Log(_db.build());
        
        log.saveEntry("Winston Churchill", false);        

        List<JSONObject> readedDatabase = _db.readDb();

        assertEquals("George Pearce", readedDatabase.get(1).get("name"));
        assertEquals(2,log.getEntries().size());
    }
    
    @Test
    public void clearLog() {
        Log log = new Log(_db.build());
        
        log.clearLog();
        
        List<JSONObject> readedDatabase = _db.readDb();

        assertEquals(0,readedDatabase.size());
        assertEquals(0,log.getEntries().size());
    }
    
    @Test
    public void printLog() {
        Log log = new Log(_db.build());
        
        String logPrint = log.printLog();
        
        assertEquals("\nBob Semple absent in date 01/09/1939 00:00:00\nGeorge Pearce absent in date 01/11/1942 00:00:00",logPrint);
    }
    

}
