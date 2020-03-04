package main_package;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestLogEntry {
    static LogEntry logEntry;

    @Before
    public static void setUpLogEntry(){
        // default values
        String speaker = "Bob Semple";
        Action action = Action.ADDED;
        @SuppressWarnings("deprecation")
        Date date = new Date(1939,9,1,0,0,0);
        logEntry = new LogEntry(speaker,action,date);
    }

    @Test
    public void testGetDate() {
        String formattedDate = logEntry.getDate();
        
        assertEquals(formattedDate, "1939-09-01 00:00:00");
    }
    
    @Test
    public void testGetEntry() {
        String entry = logEntry.getEntry();
        
        assertEquals(entry, "Bob Semple ADDED in date 1939-09-01 00:00:00");
    }
}
