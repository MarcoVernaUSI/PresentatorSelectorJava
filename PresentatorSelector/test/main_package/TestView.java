package main_package;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestView {
    public static final String Path1 = "test/candidates_test.json";
    public static final String Path2 = "test/log_test.json";
    
    @Before
    public void SetUp(){
        //Create the two database files with Bob Semple  
        new TestDatabaseBuilder(Path1).createDb("name","Bob Semple");
        new TestDatabaseBuilder(Path2).createDb("entry","Bob Semple absent in date 01/09/1939 00:00:00");
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
    public void updateList() {
        Selector selector = new Selector(Path1,Path2);
        View view = new View(selector);
        
        view.updateList();
        
        assertEquals("Bob Semple", view.getCandidateList().getModel().getElementAt(0));
        
    }
    
    @Test
    public void updateListWithAdd() {
        Selector selector = new Selector(Path1,Path2);
        View view = new View(selector);
        
        selector.add("George", "Pearce");
        view.updateList();
        
        assertEquals("Bob Semple", view.getCandidateList().getModel().getElementAt(0));
        assertEquals("George Pearce", view.getCandidateList().getModel().getElementAt(1));
        
    }
    
    @Test
    public void updateListWithRemove() {
        Selector selector = new Selector(Path1,Path2);
        View view = new View(selector);
        
        selector.remove("Bob Semple");
        view.updateList();
        
        assertEquals(0, view.getCandidateList().getModel().getSize());
    }
    
    
    @Test
    public void updateListWithAddAndRemove() {
        Selector selector = new Selector(Path1,Path2);
        View view = new View(selector);
        
        selector.add("George", "Pearce");
        selector.add("Winston", "Churchill");
        selector.remove("Bob Semple");
        view.updateList();
        

        assertEquals("George Pearce", view.getCandidateList().getModel().getElementAt(0));
        assertEquals("Winston Churchill", view.getCandidateList().getModel().getElementAt(1));
    }
}

