package main_package;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCandidate {
    
    @Test
    public void printCandidate() {
        Candidate candidate= new Candidate("Bob Semple");
        
        String candidateName = candidate.printCandidate();
        
        assertEquals("Bob Semple", candidateName);
    }
    
    @Test
    public void absentDefaultValue() {
        Candidate candidate= new Candidate("Bob Semple");
        
        boolean absentValue = candidate.isAbsent();
        
        assertEquals(false, absentValue);
    }
    
    @Test
    public void isAbsent() {
        Candidate candidate= new Candidate("Bob Semple");
        candidate.setAbsent(true);
        
        boolean absentValue = candidate.isAbsent();
        
        assertEquals(true, absentValue);
    }
}
