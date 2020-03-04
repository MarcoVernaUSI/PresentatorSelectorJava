package main_package;

import static org.junit.Assert.*;

import org.junit.Test;

import main_package.builders.CandidateBuilder;

public class TestCandidate {
    
    @Test
    public void printCandidate() {
        Candidate candidate= new CandidateBuilder().withName("Bob","Semple").build();
        
        String candidateName = candidate.printCandidate();
        
        assertEquals(candidateName, "Bob Semple");
    }
    
    @Test
    public void absentDefaultValue() {
        Candidate candidate= new CandidateBuilder().build();
        
        boolean absentValue = candidate.isAbsent();
        
        assertEquals(absentValue, false);
    }
    
    @Test
    public void isAbsent() {
        Candidate candidate= new CandidateBuilder().withAbsent(true).build();
        
        boolean absentValue = candidate.isAbsent();
        
        assertEquals(absentValue, true);
    }
}
