package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main_package.builders.JsonDatabaseBuilder;

public class TestCandidates {
    public static final String Path = JsonDatabaseBuilder.DefaultPath;
    
    public JsonDatabaseBuilder _db;
    
    @Before
    public void SetUp(){
        _db = new JsonDatabaseBuilder().ofCandidates().writeFile();
    }
    
    
    @After
    public void rollback(){
        File jsonFile = new File(Path);
        jsonFile.delete();
    }
    
    @Test
    public void loadDatabase() {
        Candidates candidates = new Candidates(_db.build());
        
        candidates.loadDatabase();
        
        assertEquals("Bob", candidates.getSpeaker(0).getFname());
        assertEquals("Semple", candidates.getSpeaker(0).getSurname());
        assertEquals("George", candidates.getSpeaker(1).getFname());
        assertEquals("Pearce", candidates.getSpeaker(1).getSurname());
    }

    @Test
    public void dumpDatabase() {
        Candidates candidates = new Candidates(_db.build());
        candidates.addSpeaker("Neville", "Chamberlain");
        candidates.addSpeaker("Winston", "Churchill");
        
        candidates.dumpDatabase();
        
        
        List<JSONObject> readedDatabase = _db.readDb();
        
        assertEquals("Bob", readedDatabase.get(0).get("fname"));
        assertEquals("Semple", readedDatabase.get(0).get("surname"));
        assertEquals("George", readedDatabase.get(1).get("fname"));
        assertEquals("Pearce", readedDatabase.get(1).get("surname"));  
    
        assertEquals("Neville", readedDatabase.get(2).get("fname"));
        assertEquals("Chamberlain", readedDatabase.get(2).get("surname"));
        assertEquals("Winston", readedDatabase.get(3).get("fname"));
        assertEquals("Churchill", readedDatabase.get(3).get("surname"));  
    }

    @Test
    public void addSpeaker() {
        Candidates candidates = new Candidates(_db.build());
        
        candidates.addSpeaker("Neville", "Chamberlain");
        
        assertEquals("Neville", candidates.getSpeaker(2).getFname());
        assertEquals("Chamberlain", candidates.getSpeaker(2).getSurname());
        assertEquals(3,candidates.printCandidates().size());
    }
    
    @Test
    public void removeSpeaker() {
        Candidates candidates = new Candidates(_db.build());
        
        candidates.removeSpeakers("Bob Semple");
        
        assertEquals("George", candidates.getSpeaker(0).getFname());
        assertEquals("Pearce", candidates.getSpeaker(0).getSurname());
        assertEquals(1,candidates.printCandidates().size());
    }

    @Test
    public void setAbsent() {
        Candidates candidates = new Candidates(_db.build());
        
        candidates.setAbsent("George Pearce");
        
        assertEquals(true,candidates.getSpeaker(1).isAbsent());
    }

    @Test
    public void checkAbsent() {
        Candidates candidates = new Candidates(_db.build());
        
        candidates.checkAbsent("George Pearce");
        
        assertEquals(false, candidates.getSpeaker(1).isAbsent());
    }

    @Test
    public void printCandidates() {
        Candidates candidates = new Candidates(_db.build());
        
        List<String>candidatesList = candidates.printCandidates();
        
        assertEquals("Bob Semple", candidatesList.get(0));
        assertEquals("George Pearce", candidatesList.get(1));
    }
    
    @Test
    public void getRandomSpeaker() {
        Candidates candidates = new Candidates(_db.build());
        
        Candidate randomSpeaker = candidates.getRandomSpeaker();
        
        assertTrue((randomSpeaker.getFname().equals("Bob")) && (randomSpeaker.getSurname().equals("Semple")) 
            || (randomSpeaker.getFname().equals("George")) && (randomSpeaker.getSurname().equals("Pearce")));
    }
}
