package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main_package.builders.FakeJsonDatabase;

public class TestSelector {
    public static final String Path1 = "test/candidates_test.json";
    public static final String Path2 = "test/log_test.json";
    
    public FakeJsonDatabase _db_candidates;
    public FakeJsonDatabase _db_log;
    
    @Before
    public void SetUp(){
        _db_candidates = new FakeJsonDatabase().ofCandidates().withPath(Path1).writeFile();
        _db_log = new FakeJsonDatabase().ofLogEntries().withPath(Path2).writeFile();
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
        selector.remove("Edward Wood");
        
        assertEquals("Bob Semple", selector.getSpeakers().get(0));
        assertEquals("George Pearce",selector.getSpeakers().get(1));
        assertEquals("Winston Churchill", selector.getSpeakers().get(2));   
    }
    
    @Test
    public void saveLogEntry() {
        Selector selector = new Selector(Path1,Path2);
        selector.getLog().clearLog();
        
        selector.setAbsent("Bob Semple");
        Date date = new Date();
        String log = selector.printLog();
        
        assertEquals("\nBob Semple absent in date "+ Log.DateFormat.format(date),log);    
    }
    
    @Test
    public void saveMultipleEntries() {
        Selector selector = new Selector(Path1,Path2);
        selector.getLog().clearLog();
        
        selector.setAbsent("Bob Semple");
        Date date1 = new Date();
        selector.setAbsent("George Pearce");
        Date date2 = new Date();
        String log = selector.printLog();
        
        assertEquals("\nBob Semple absent in date "+ Log.DateFormat.format(date1)+
            "\nGeorge Pearce absent in date "+ Log.DateFormat.format(date2),log);   
    }
    
    public void addAndSaveEntry() {
        Selector selector = new Selector(Path1,Path2);
        selector.getLog().clearLog();
        
        selector.add("Edward", "Wood");
        selector.setAbsent("Edward Wood");
        Date date = new Date();
        String log = selector.printLog();
        
        assertEquals("\nEdward Wood absent in date "+ Log.DateFormat.format(date),log);
    }
    

    public void notSaveEntryIfNotAbsent() {
        Selector selector = new Selector(Path1,Path2);
        selector.getLog().clearLog();
        
        selector.setAbsent("Bob Semple");
        String log1 = selector.printLog();
        selector.setAbsent("Bob Semple");
        String log2 = selector.printLog();
            
        assertEquals(log1,log2);
        }
    
    
    @Test
    public void correctSelectionWithAbsents() {
        Selector selector = new Selector(Path1,Path2);
        
        selector.setAbsent("George Pearce");
        String random = selector.select();
        
        assertEquals("Bob Semple",random);
    }
    
    @Test
    public void correctSelectionWithAbsentsAfterAdd() {
        Selector selector = new Selector(Path1,Path2);
        
        selector.setAbsent("George Pearce");
        selector.add("Edward", "Wood");
        String random = selector.select();
        
        assertTrue(new ArrayList<String>()
        {{add("Bob Semple");add("Edward Wood");}}.contains(random));
        }

    @Test
    public void correctSelectionWithAbsentsAfterAddAndRemove() {
        Selector selector = new Selector(Path1,Path2);
        
        selector.setAbsent("George Pearce");
        selector.add("Edward", "Wood");
        selector.remove("Bob Semple");
        String random = selector.select();
        
        assertEquals("Edward Wood",random);
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
