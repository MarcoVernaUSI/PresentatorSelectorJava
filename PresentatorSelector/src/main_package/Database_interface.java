package main_package;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// Class for interact with the json list containing the candidates
public class Database_interface {
    private final String path;
    private final String default_path; 
    private final JSONParser jsonParser = new JSONParser();
    
    
    public Database_interface(String path) {
        super();
        this.path = path;
        this.default_path = StringUtils.substring(path, 0, path.length() - 5) + " default.json";
    }
    
    // Load the database into the list
    public ArrayList<Candidate> load_database(boolean _default) {       
        ArrayList<Candidate> database = new ArrayList<>();
        
        try (FileReader reader = new FileReader(this.path))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            
            JSONArray candidatesList = (JSONArray) obj;
            for (Object object : candidatesList) {
                database.add(parseCandidate((JSONObject) object));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        } 
        return database;
    }
    
    // Method overloading to let the default value
    public ArrayList<Candidate> load_database() {
        boolean _default = false;
        return load_database(_default);
    }
    
    // Dump the list to the database
    public void update_database(ArrayList<Candidate> speakers) {
        JSONArray speaker_list = new JSONArray();
        for (Candidate candidate : speakers) {
            JSONObject speaker = new JSONObject();
            speaker.put("fname",candidate.getFname());
            speaker.put("surname",candidate.getSurname());
            speaker_list.add(speaker);
        }
        //Write JSON file
        try (FileWriter file = new FileWriter(this.path)) {
            file.write(speaker_list.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Candidate parseCandidate(JSONObject obj) {
        return new Candidate((String) obj.get("fname"), (String) obj.get("surname"));
    }
}