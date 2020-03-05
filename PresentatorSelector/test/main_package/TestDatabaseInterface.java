package main_package;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main_package.builders.DatabaseInterfaceBuilder;

public class TestDatabaseInterface {
    Object dbCopy;
    private static final String defaultPath = new DatabaseInterfaceBuilder().build().getPath();
    
    // Faccio copia del json di test
    @Before
    public void copyDb(){
        try (FileReader reader = new FileReader(defaultPath))
        {
            dbCopy = new JSONParser().parse(reader);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
    }
    
    // Ripristino Json di test
    @After
    public void rollback(){
        try (FileWriter file = new FileWriter(defaultPath)) {
                file.write(((JSONArray) dbCopy).toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
    // READ FILE TEST
    @Test
    public void loadDatabase() {
        Database dbi = new DatabaseInterfaceBuilder().build();
        
        List<Candidate> candidatesList=dbi.load();
        
        // DOMANDA QUI USO print candidate, viene testato?
        assertEquals(candidatesList.get(0).printCandidate(), "Bob Semple");
        assertEquals(candidatesList.get(1).printCandidate(), "George Pearce");  
     }
    
    // WRITE FILE TEST
    @Test
    public void updateDatabase() {
        Candidate entry1 = new Candidate("Neville", "Chamberlain");
        Candidate entry2 = new Candidate("Winston", "Churchill");
        List<Candidate> candidateList = new ArrayList<>();
        candidateList.add(entry1);
        candidateList.add(entry2);
        Database dbi = new DatabaseInterfaceBuilder().build();
        
        // Per testare il write per forza devo testate anche il read
        dbi.update(candidateList);
        List<Candidate> savedList=dbi.load();
        
        assertEquals(savedList.get(0).printCandidate(), "Neville Chamberlain");
        assertEquals(savedList.get(1).printCandidate(), "Winston Churchill");  
      }

}
