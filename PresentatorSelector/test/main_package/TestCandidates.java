package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCandidates {
    public static final String DefaultPath = "test/database_test.json";
    public Candidates _candidates;
    
    @Before
    public void SetUp(){
        //Create the file with Bob Semple inside
        new TestDatabase(DefaultPath).add("name","Bob Semple");
        _candidates = new Candidates(DefaultPath);
    }
    
    @After
    public void rollback(){
        File file = new File(DefaultPath);
        file.delete();
    }

    @Test
    public void addMultipleSpeaker() {
        
        _candidates.addSpeaker("George Pearce");
        _candidates.addSpeaker("Winston Churchill");
        
        assertEquals("Bob Semple", _candidates.getSpeaker(0).printCandidate());
        assertEquals("George Pearce", _candidates.getSpeaker(1).printCandidate());
        assertEquals("Winston Churchill", _candidates.getSpeaker(2).printCandidate());
        assertEquals(3, _candidates.printCandidates().size());
        
    }
    
    @Test
    public void removeSpeaker() {
        _candidates.addSpeaker("George Pearce");
        
        _candidates.removeSpeaker("Bob Semple");
        
        assertEquals("George Pearce", _candidates.getSpeaker(0).printCandidate());
        assertEquals(1, _candidates.printCandidates().size());
    }

    @Test
    public void setAbsent() {
        
        _candidates.setAbsent("Bob Semple");
        
        assertEquals(true,_candidates.getSpeaker(0).isAbsent());
    }

    @Test
    public void checkAbsent() {
        
        assertEquals(false, _candidates.getSpeaker(0).isAbsent());
    }

    @Test
    public void printCandidates() {
        _candidates.addSpeaker("George Pearce");
        
        List<String>candidatesList = _candidates.printCandidates();
        
        assertEquals("Bob Semple", candidatesList.get(0));
        assertEquals("George Pearce", candidatesList.get(1));
    }
    
    @Test
    public void getRandomSpeaker() {
        _candidates.addSpeaker("George Pearce");
        
        Candidate randomSpeaker = _candidates.getRandomSpeaker();
        
        assertTrue(new ArrayList<String>()
        {{add("Bob Semple");add("George Pearce");}}
        .contains(randomSpeaker.printCandidate()));
        }
}
