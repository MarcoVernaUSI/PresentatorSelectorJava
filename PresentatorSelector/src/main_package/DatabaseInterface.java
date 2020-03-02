package main_package;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// Class for interact with the json list containing the candidates
public class DatabaseInterface {
    private final String path;
    private final JSONParser jsonParser = new JSONParser();
    
    
    public DatabaseInterface(String path) {
        this.path = path;
    }
    
    // Load the database into the list
    public List<Candidate> load_database() {       
        List<Candidate> database = new ArrayList<>();
        String currentPath = path;

        try (FileReader reader = new FileReader(currentPath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            
            JSONArray candidatesList = (JSONArray) obj;
            for (Object object : candidatesList) {
                database.add(parseCandidate((JSONObject) object));
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }  
        return database;
    }
    
    // Dump the list to the database
    public void updateDatabase(List<Candidate> speakers) {
        JSONArray speakerList = new JSONArray();
        for (Candidate candidate : speakers) {
            JSONObject speaker = new JSONObject();
            speaker.put("fname",candidate.getFname());
            speaker.put("surname",candidate.getSurname());
            speakerList.add(speaker);
        }
        //Write JSON file
        try (FileWriter file = new FileWriter(path)) {
            file.write(speakerList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Return a string with the candidate name and surname
    private Candidate parseCandidate(JSONObject obj) {
        return new Candidate((String) obj.get("fname"), (String) obj.get("surname"));
    }
}