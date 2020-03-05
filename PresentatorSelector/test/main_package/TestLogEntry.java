package main_package;

import static org.junit.Assert.*;

import org.junit.Test;

import main_package.builders.CandidateBuilder;

public class TestLogEntry {
   
    CandidateBuilder _candidateBuilder = new CandidateBuilder();
    
    @Test
    public void getEntry() {
        String date = "01/09/1939 00:00:00";
        Candidate speaker = _candidateBuilder.build();
        LogEntry logEntry = new LogEntry(speaker.printCandidate(),date);
        
        
        String entry = logEntry.getEntry();

        assertEquals(entry, "Bob Semple absent in date 01/09/1939 00:00:00");
    }
}
