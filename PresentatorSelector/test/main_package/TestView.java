package main_package;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main_package.builders.JsonDatabaseBuilder;

public class TestView {
    public static final String Path1 = "test/candidates_test.json";
    public static final String Path2 = "test/log_test.json";
    
    public JsonDatabaseBuilder _dbCandidates;
    public JsonDatabaseBuilder _dbLog;
    
    @Before
    public void SetUp(){
        _dbCandidates = new JsonDatabaseBuilder().ofCandidates().withPath(Path1).writeFile();
        _dbLog = new JsonDatabaseBuilder().ofLogEntries().withPath(Path2).writeFile();
    }
    
    @Test
    public void updateList() {
        View view = new View(new Selector(Path1,Path2));
        
        view.updateList();
        
        assertEquals(view.getCandidateList().getModel().getElementAt(0), "Bob Semple");
        assertEquals(view.getCandidateList().getModel().getElementAt(1), "George Pearce");

    }
}

