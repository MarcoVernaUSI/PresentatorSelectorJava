package main_package;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestCandidate {
    
    Candidate candidate;
    
    @Before
    public void setUpCandidate(){
        candidate = new Candidate("Bob","Semple");
    }

    @Test
    public void testPrintCandidate() {
        String candidateName = candidate.printCandidate();
        
        assertEquals(candidateName, "Bob Semple");
    }
    
    @Test
    public void testAbsentDefaultValue() {
        boolean absentValue = candidate.isAbsent();
        
        assertEquals(absentValue, false);
    }
}
