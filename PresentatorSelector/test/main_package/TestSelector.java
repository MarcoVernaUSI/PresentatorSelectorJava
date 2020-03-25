package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSelector {
    public static final String Path1 = "test/candidates_test.json";
    public static final String Path2 = "test/log_test.json";
    public Selector _selector;
    
    @Before
    public void SetUp(){
        //Create the two database files with Bob Semple
        new TestDatabase(Path1).add("name","Bob Semple");
        new TestDatabase(Path2).add("entry","Bob Semple absent in date 01/09/1939 00:00:00");
        _selector = new Selector(Path1,Path2);
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
        
        String speaker = _selector.select();
        
        assertEquals("Bob Semple", speaker);
    }
    
    @Test
    public void multipleSelect() {
        _selector.add("George Pearce");
        
        String speaker = _selector.select();
        
        assertTrue((speaker.equals("Bob Semple")) 
            || (speaker.equals("George Pearce")));
    }
    
    
    @Test
    public void getSpeakers() {
        _selector.add("George Pearce");
        
        List<String> speakerList = _selector.getSpeakers();
        
        assertEquals(2, speakerList.size());
        assertEquals("Bob Semple", speakerList.get(0));
        assertEquals("George Pearce", speakerList.get(1));
    }
    
    @Test
    public void remove(){
        _selector.add("George Pearce");
        
        _selector.remove("Bob Semple");
        
        assertEquals(1, _selector.getSpeakers().size());
        assertEquals("George Pearce", _selector.getSpeakers().get(0));
    }
    
    @Test
    public void add(){
        
        _selector.add("George Pearce");
        
        assertEquals(2, _selector.getSpeakers().size());
        assertEquals("George Pearce", _selector.getSpeakers().get(1));
    }
    
    @Test
    public void addEqual(){
        
        _selector.add("Bob Semple");
        
        assertEquals(1, _selector.getSpeakers().size());
        assertEquals("Bob Semple", _selector.getSpeakers().get(0));
    }
    
    @Test
    public void printLog() {
        String logPrint = _selector.printLog();
        
        assertEquals("\nBob Semple absent in date 01/09/1939 00:00:00",logPrint);
    }
    
    @Test
    public void setAbsent() {
        _selector.setAbsent("Bob Semple");
        
        assertTrue(_selector.checkAbsent("Bob Semple"));
    }

    @Test
    public void checkAbsent() {
        _selector.setAbsent("Bob Semple");
        
        boolean value = _selector.checkAbsent("Bob Semple");

        assertTrue(value);
    }
    
    ///////////////////
    @Test
    public void loadAndRemove() {
        
        _selector.add("Edward Wood");
        _selector.add("Winston Churchill");
        _selector.remove("Edward Wood");
        
        assertEquals("Bob Semple", _selector.getSpeakers().get(0));
        assertEquals("Winston Churchill", _selector.getSpeakers().get(1));   
    }
    
    @Test
    public void saveLogEntry() {
        _selector.clearLog();
        
        _selector.setAbsent("Bob Semple");
        Date date = new Date();
        String log = _selector.printLog();
        
        assertEquals("\nBob Semple absent in date "+ Log.DateFormat.format(date),log);    
    }
    
    @Test
    public void saveMultipleEntries() {
        _selector.clearLog();
        _selector.add("George Pearce");
        
        _selector.setAbsent("Bob Semple");
        Date date1 = new Date();
        _selector.setAbsent("George Pearce");
        Date date2 = new Date();
        String log = _selector.printLog();
        
        assertEquals("\nBob Semple absent in date "+ Log.DateFormat.format(date1)+
            "\nGeorge Pearce absent in date "+ Log.DateFormat.format(date2),log);   
    }
    
    public void addAndSaveEntry() {
        _selector.clearLog();
        
        _selector.add("Edward Wood");
        _selector.setAbsent("Edward Wood");
        Date date = new Date();
        String log = _selector.printLog();
        
        assertEquals("\nEdward Wood absent in date "+ Log.DateFormat.format(date),log);
    }
    

    public void notSaveEntryIfNotAbsent() {
        _selector.clearLog();
        
        _selector.setAbsent("Bob Semple");
        String log1 = _selector.printLog();
        _selector.setAbsent("Bob Semple");
        String log2 = _selector.printLog();
            
        assertEquals(log1,log2);
        }
    
    
    @Test
    public void correctSelectionWithAbsents() {
        _selector.add("George Pearce");
        
        _selector.setAbsent("George Pearce");
        String random = _selector.select();
        
        assertEquals("Bob Semple",random);
    }
    
    @Test
    public void correctSelectionWithAbsentsAfterAdd() {
        _selector.add("George Pearce");
        
        _selector.setAbsent("George Pearce");
        _selector.add("Edward Wood");
        String random = _selector.select();
        
        assertTrue(new ArrayList<String>()
        {{add("Bob Semple");add("Edward Wood");}}.contains(random));
        }

    @Test
    public void correctSelectionWithAbsentsAfterAddAndRemove() {
        _selector.add("George Pearce");
        
        _selector.setAbsent("George Pearce");
        _selector.add("Edward Wood");
        _selector.remove("Bob Semple");
        String random = _selector.select();
        
        assertEquals("Edward Wood",random);
    }
    
    @Test
    public void SelectionAllAbsents() {
        
        _selector.setAbsent("Bob Semple");
        String random1 = _selector.select();
      
        assertEquals("No speaker avaiable!", random1);
    }
}
