package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main_package.builders.FakeJsonDatabase;

public class TestLog {
    public static final String Path = FakeJsonDatabase.DefaultPath;
    public static final Log Log = new Log();
    public FakeJsonDatabase _db;
    
    
    @Before
    public void setUp() {
        _db = new FakeJsonDatabase().ofLogEntries().writeFile();
        Log.loadDatabase(_db.build());
    }
    
    @After
    public void rollback(){
        File jsonFile = new File(Path);
        jsonFile.delete();
    }
    
    @Test
    public void loadDatabase() {
        
        assertEquals("Bob Semple",Log.getEntries().get(0).getKey());
        assertEquals("01/09/1939 00:00:00", Log.getEntries().get(0).getValue());
        assertEquals("George Pearce", Log.getEntries().get(1).getKey());
        assertEquals("01/11/1942 00:00:00", Log.getEntries().get(1).getValue());
    }

    @Test
    public void dumpDatabase() {
        Log.saveEntry("Neville Chamberlain", true);
        Log.saveEntry("Winston Churchill", true);
        
        Log.dumpDatabase();
        
        List<JSONObject> readedDatabase = _db.readDb();

        assertEquals("Bob Semple", readedDatabase.get(0).get("name"));
        assertEquals("George Pearce", readedDatabase.get(1).get("name"));
        assertEquals("Neville Chamberlain", readedDatabase.get(2).get("name"));
        assertEquals("Winston Churchill", readedDatabase.get(3).get("name"));  
    }
    
    
    @Test
    public void saveEntryTrue() {
        
        Log.saveEntry("Neville Chamberlain", true);        

        List<JSONObject> readedDatabase = _db.readDb();
        
        assertEquals("George Pearce", readedDatabase.get(1).get("name"));
        assertEquals("Neville Chamberlain", readedDatabase.get(2).get("name"));
        assertEquals(3,Log.getEntries().size());
    }
    

    @Test
    public void saveEntryFalse() {
        
        Log.saveEntry("Winston Churchill", false);        

        List<JSONObject> readedDatabase = _db.readDb();

        assertEquals("George Pearce", readedDatabase.get(1).get("name"));
        assertEquals(2,Log.getEntries().size());
    }
    
    @Test
    public void clearLog() {
        
        Log.clearLog();
        
        List<JSONObject> readedDatabase = _db.readDb();

        assertEquals(0,readedDatabase.size());
        assertEquals(0,Log.getEntries().size());
    }
    
    @Test
    public void printLog() {
        
        String logPrint = Log.printLog();
        
        assertEquals("\nBob Semple absent in date 01/09/1939 00:00:00\nGeorge Pearce absent in date 01/11/1942 00:00:00",logPrint);
    }
    

}
