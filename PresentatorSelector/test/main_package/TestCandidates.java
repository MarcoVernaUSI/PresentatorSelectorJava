package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestCandidates {
    public static final String DefaultPath = "test/database_test.json";
    public Candidates _candidates;
    
    @Before
    public void SetUp(){
        //Create the file with Bob Semple inside
        JSONObject candidate = new JSONObject();
        candidate.put("fname","Bob");
        candidate.put("surname","Semple");
        JSONArray objectsList = new JSONArray();
        objectsList.add(candidate);
        try (FileWriter file = new FileWriter(DefaultPath)) {
            file.write(objectsList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        _candidates = new Candidates(DefaultPath);
    }
    
    @After
    public void rollback(){
        File file = new File(DefaultPath);
        file.delete();
    }
    
    @Test
    public void UpdateAndLoadDatabase() {
        _candidates.getDatabase().clear(); //svuoto la lista, se non sarà vuota è perchè la ricarica
        
        _candidates.load();
        
        assertEquals("Bob", _candidates.getSpeaker(0).getFname());
        assertEquals("Semple", _candidates.getSpeaker(0).getSurname());
    }


    @Test
    public void addMultipleSpeaker() {
        
        _candidates.addSpeaker("George", "Pearce");
        _candidates.addSpeaker("Winston", "Churchill");
        
        assertEquals("Bob Semple", _candidates.getSpeaker(0).printCandidate());
        assertEquals("George Pearce", _candidates.getSpeaker(1).printCandidate());
        assertEquals("Winston Churchill", _candidates.getSpeaker(2).printCandidate());
        assertEquals(3, _candidates.getDatabase().size());
        
    }
    
    @Test
    public void removeSpeaker() {
        _candidates.addSpeaker("George", "Pearce");
        
        
        _candidates.removeSpeakers("Bob Semple");
        
        assertEquals("George Pearce", _candidates.getSpeaker(0).printCandidate());
        assertEquals(1, _candidates.getDatabase().size());
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
        _candidates.addSpeaker("George", "Pearce");
        
        List<String>candidatesList = _candidates.printCandidates();
        
        assertEquals("Bob Semple", candidatesList.get(0));
        assertEquals("George Pearce", candidatesList.get(1));
    }
    
    @Test
    public void getRandomSpeaker() {
        _candidates.addSpeaker("George", "Pearce");
        
        Candidate randomSpeaker = _candidates.getRandomSpeaker();
        
        assertTrue(new ArrayList<String>()
        {{add("Bob Semple");add("George Pearce");}}
        .contains(randomSpeaker.getFname()+" "+randomSpeaker.getSurname()));
        }
}
