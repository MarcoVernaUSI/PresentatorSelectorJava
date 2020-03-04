package main_package;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main_package.builders.LogBuilder;

public class TestLog {
    Object logCopy;
    private static final String defaultPath = new LogBuilder().build().getPath();
    
    // Faccio copia del json di test
    @Before
    public void copyLog(){
        try (FileReader reader = new FileReader(defaultPath))
        {
            logCopy = new JSONParser().parse(reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
    }
    
    // Ripristino Json di test
    @After
    public void rollback(){
        try (FileWriter file = new FileWriter(defaultPath)) {
                file.write(((JSONArray) logCopy).toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
    // READ FILE TEST
    @Test
    public void loadLog() {
        Log log = new LogBuilder().build();
        
        List<LogEntry> log_list=log.loadLog();
        
        // DOMANDA QUI USO GET ENTRY, viene testato?
        assertEquals(log_list.get(0).getEntry(),"Bob Semple ADDED in date 01/09/1939 00:00:00");
        assertEquals(log_list.get(1).getEntry(),"George Pearce REMOVED in date 02/11/1932 00:00:00");  
     }
    
    // WRITE FILE TEST
    @Test
    public void updateLog() {
        LogEntry entry1 = new LogEntry("Neville Chamberlain", Action.REMOVED, "10/05/1940 00:00:00");
        LogEntry entry2 = new LogEntry("Winston Churchill", Action.ADDED, "10/05/1940 00:00:00");
        Log log = new LogBuilder().withEntries(entry1, entry2).build();
        
        // Per testare il write per forza devo testate anche il read
        log.updateLog();
        List<LogEntry> log_list=log.loadLog();
        
        // DOMANDA QUI USO GET ENTRY, viene testato?
        assertEquals(log_list.get(0).getEntry(),"Neville Chamberlain REMOVED in date 10/05/1940 00:00:00");
        assertEquals(log_list.get(1).getEntry(),"Winston Churchill ADDED in date 10/05/1940 00:00:00");  
     }
      
    @Test
    public void saveEntry() {
        Log log = new LogBuilder().build();
        int log_length= log.getLog().size();
        Candidate _candidate = new Candidate("Neville", "Chamberlain");
        
        log.saveEntry(_candidate, Action.REMOVED);
        
        assertEquals(log.getLog().get(log_length).getSpeaker(),"Neville Chamberlain");
        assertEquals(log.getLog().get(log_length).getAction(),Action.REMOVED);
        assertNotNull(log.getLog().get(log_length).getDate());
     }

    @Test
    public void clearLog() {
        Log log = new LogBuilder().build();
        
        log.clearLog();
        
        assertEquals(log.getLog().size(), 0);
        // Qui testo anche load log
        assertEquals(log.loadLog().size(), 0);   
     }

    @Test
    public void printLog() {
        LogEntry entry1 = new LogEntry("Neville Chamberlain", Action.REMOVED, "10/05/1940 00:00:00");
        LogEntry entry2 = new LogEntry("Winston Churchill", Action.ADDED, "10/05/1940 00:00:00");
        Log log = new LogBuilder().withEntries(entry1, entry2).build();
        
        String logPrint = log.printLog();
        
        assertEquals(logPrint,"\nNeville Chamberlain REMOVED in date 10/05/1940 00:00:00\nWinston Churchill ADDED in date 10/05/1940 00:00:00");        
     }
}
