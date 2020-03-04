package main_package;

import static org.junit.Assert.*;

import org.junit.Test;

import main_package.builders.LogEntryBuilder;

public class TestLogEntry {
   

    @Test
    public void getDate() {
        LogEntry logEntry = new LogEntryBuilder().withDate("01/09/1939 00:00:00").build();
        
        String formattedDate = logEntry.getDate();
        
        assertEquals(formattedDate, "01/09/1939 00:00:00");
    }
    
    @Test
    public void getEntry() {
        LogEntry logEntry = new LogEntryBuilder()
            .withDate("01/09/1939 00:00:00")
            .withAction(Action.ADDED)
            .withSpeaker("Bob Semple").build();
        
        String entry = logEntry.getEntry();
        
        assertEquals(entry, "Bob Semple ADDED in date 01/09/1939 00:00:00");
    }
}
