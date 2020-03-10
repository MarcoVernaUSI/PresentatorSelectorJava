package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main_package.builders.FakeJsonDatabase;

public class TestCandidates {
    public static final String Path = FakeJsonDatabase.DefaultPath;
    public static final Candidates Candidates = new Candidates();
    public FakeJsonDatabase _db;
    
    @Before
    public void SetUp(){
        _db = new FakeJsonDatabase().ofCandidates().writeFile();
        Candidates.loadDatabase(_db.build());
    }
    
    
    @After
    public void rollback(){
        File jsonFile = new File(Path);
        jsonFile.delete();
    }
    
    @Test
    public void loadDatabase() {
        
        assertEquals("Bob", Candidates.getSpeaker(0).getFname());
        assertEquals("Semple", Candidates.getSpeaker(0).getSurname());
        assertEquals("George", Candidates.getSpeaker(1).getFname());
        assertEquals("Pearce", Candidates.getSpeaker(1).getSurname());
    }

    @Test
    public void dumpDatabase() {
        Candidates.addSpeaker("Neville", "Chamberlain");
        Candidates.addSpeaker("Winston", "Churchill");
        
        Candidates.dumpDatabase();
        
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
        Candidates.addSpeaker("Neville", "Chamberlain");
        
        assertEquals("Neville", Candidates.getSpeaker(2).getFname());
        assertEquals("Chamberlain", Candidates.getSpeaker(2).getSurname());
        assertEquals(3,Candidates.printCandidates().size());
    }
    
    @Test
    public void removeSpeaker() {
        Candidates.removeSpeakers("Bob Semple");
        
        assertEquals("George", Candidates.getSpeaker(0).getFname());
        assertEquals("Pearce", Candidates.getSpeaker(0).getSurname());
        assertEquals(1,Candidates.printCandidates().size());
    }

    @Test
    public void setAbsent() {
        Candidates.setAbsent("George Pearce");
        
        assertEquals(true,Candidates.getSpeaker(1).isAbsent());
    }

    @Test
    public void checkAbsent() {
        Candidates.checkAbsent("George Pearce");
        
        assertEquals(false, Candidates.getSpeaker(1).isAbsent());
    }

    @Test
    public void printCandidates() {
        List<String>candidatesList = Candidates.printCandidates();
        
        assertEquals("Bob Semple", candidatesList.get(0));
        assertEquals("George Pearce", candidatesList.get(1));
    }
    
    @Test
    public void getRandomSpeaker() {
        Candidate randomSpeaker = Candidates.getRandomSpeaker();
        
        assertTrue(new ArrayList<String>()
        {{add("Bob Semple");add("George Pearce");}}
        .contains(randomSpeaker.getFname()+" "+randomSpeaker.getSurname()));
        }
}
