package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main_package.PresentatorMain.Selector;
import main_package.builders.JsonDatabaseBuilder;

public class TestSelector {
    public static final String Path1 = "test/candidates_test.json";
    public static final String Path2 = "test/log_test.json";
    
    public JsonDatabaseBuilder _db_candidates;
    public JsonDatabaseBuilder _db_log;
    
    @Before
    public void SetUp(){
        _db_candidates = new JsonDatabaseBuilder().ofCandidates().withPath(Path1).writeFile();
        _db_log = new JsonDatabaseBuilder().ofLogEntries().withPath(Path2).writeFile();
    }
    
    // Cancello file
    @After
    public void rollback(){
        File jsonFile1 = new File(Path1);
        File jsonFile2 = new File(Path2);
        jsonFile1.delete();
        jsonFile2.delete();
    }
    
    
    @Test
    public void select() {
        
        Selector selector = new Selector(Path1,Path2);
        
        String speaker = selector.select();
        
        assertTrue((speaker.equals("Bob Semple")) 
            || (speaker.equals("George Pearce")));
    }
    
    @Test
    public void getSpeakers() {
        Selector selector = new Selector(Path1,Path2);
        
        List<String> speakerList = selector.getSpeakers();
        
       assertEquals(2, speakerList.size());
       assertEquals("Bob Semple", speakerList.get(0));
       assertEquals("George Pearce", speakerList.get(1));
    }
    
    @Test
    public void remove(){
        Selector selector = new Selector(Path1,Path2);
        
        selector.remove("Bob Semple");
        
       assertEquals(1, selector.getSpeakers().size());
       assertEquals("George Pearce", selector.getSpeakers().get(0));
    }
    
    @Test
    public void add(){

    }
    
    @Test
    public void printLog() {
    
    }
    
    @Test
    public void setAbsent() {
    
    }

    @Test
    public void checkAbsent() {
 
    }
}
