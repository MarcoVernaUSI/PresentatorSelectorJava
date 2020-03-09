package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.text.ParseException;
import java.util.Date;
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
        System.out.println(speakerList);
        
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
        Selector selector = new Selector(Path1,Path2);
        
        selector.add("Winston" , "Churchill");
        
        assertEquals(3, selector.getSpeakers().size());
        assertEquals("Winston Churchill", selector.getSpeakers().get(2));
    }
    
    @Test
    public void printLog() {
        Selector selector = new Selector(Path1,Path2);
        
        String logPrint = selector.printLog();
        
        assertEquals("\nBob Semple absent in date 01/09/1939 00:00:00\nGeorge Pearce absent in date 01/11/1942 00:00:00",logPrint);
    }
    
    @Test
    public void setAbsent() {
        Selector selector = new Selector(Path1,Path2);
        
        selector.setAbsent("Bob Semple");
        
        assertTrue(selector.checkAbsent("Bob Semple"));
    
    }

    @Test
    public void checkAbsent() {
        Selector selector = new Selector(Path1,Path2);
        selector.setAbsent("Bob Semple");
        
        boolean value = selector.checkAbsent("Bob Semple");

        assertTrue(value);
    }
    
    
    ///////////////////
    @Test
    public void loadAndRemove() {
        Selector selector = new Selector(Path1,Path2);
        
        selector.add("Edward", "Wood");
        selector.add("Winston", "Churchill");
        selector.add("Neville", "Chamberlain");
        selector.remove("Edward Wood");
        
        assertEquals("Bob Semple", selector.getSpeakers().get(0));
        assertEquals("George Pearce",selector.getSpeakers().get(1));
        assertEquals("Winston Churchill", selector.getSpeakers().get(2));
        assertEquals("Neville Chamberlain", selector.getSpeakers().get(3));
        
    }
    
    @Test
    public void logAndAbsents() throws ParseException {
        Selector selector = new Selector(Path1,Path2);
        selector.getLog().clearLog();
        
        selector.add("Edward", "Wood");
        selector.add("Winston", "Churchill");
        selector.add("Neville", "Chamberlain");
        selector.setAbsent("Edward Wood");
        Date date1 = new Date();
        selector.setAbsent("Bob Semple");
        Date date2 = new Date();
        String log1 = selector.printLog();
        selector.setAbsent("Edward Wood");
        String log2 = selector.printLog();
        selector.setAbsent("Neville Chamberlain");
        Date date3 = new Date();
        
        String log3 = selector.printLog();
        
        assertEquals("\nEdward Wood absent in date "+ LogEntry.DateFormat.format(date1)+
            "\nBob Semple absent in date "+ LogEntry.DateFormat.format(date2),log1);
        assertEquals(log1,log2);
        assertEquals("\nEdward Wood absent in date "+ LogEntry.DateFormat.format(date1)+
            "\nBob Semple absent in date "+ LogEntry.DateFormat.format(date2)+ 
            "\nNeville Chamberlain absent in date "+LogEntry.DateFormat.format(date3),log3);
        
    }
    
    @Test
    public void correctSelectionWithAbsents() {
        Selector selector = new Selector(Path1,Path2);
        
        selector.add("Edward", "Wood");
        selector.add("Winston", "Churchill");
        selector.add("Neville", "Chamberlain");
        selector.setAbsent("Bob Semple");
        selector.setAbsent("George Pearce");
        String random1 = selector.select();
        selector.setAbsent("Winston Churchill");
        selector.setAbsent("Neville Chamberlain");
        selector.setAbsent("George Pearce");
        String random2 = selector.select();
        selector.setAbsent("Edward Wood");
        String random3 = selector.select();
        
        assertTrue((random1.equals("Edward Wood")) 
            || (random1.equals("Winston Churchill"))
            || (random1.equals("Neville Chamberlain"))          
            );
        assertTrue((random2.equals("Edward Wood")) 
            || (random2.equals("George Pearce"))
            );
        assertTrue((random1.equals("Edward Wood")) 
            || (random1.equals("Winston Churchill"))
            || (random1.equals("Neville Chamberlain"))          
            );
        assertTrue(random3.equals("George Pearce"));
    }
    
    @Test
    public void SelectionAllAbsents() {
        Selector selector = new Selector(Path1,Path2);
        
        selector.setAbsent("Bob Semple");
        selector.setAbsent("George Pearce");
        String random1 = selector.select();
      
        assertEquals("No speaker avaiable!", random1);
    }
}
