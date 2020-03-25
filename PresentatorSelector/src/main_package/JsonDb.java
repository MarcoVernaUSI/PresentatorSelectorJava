package main_package;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// Class for interact with a json database
public class JsonDb<T> {
    private final String _path;
    private final JSONParser _jsonParser = new JSONParser();
    private final JsonObjectParser<T> _jParser;  
    
    
    public JsonDb(String path, JsonObjectParser<T> jParser) {
        _path = path;
        _jParser = jParser;
    }

    
    // Load the database into the list
    public List<T> load() {       
        List<T> database = new ArrayList<>();
        try (FileReader reader = new FileReader(_path)){
            //Read JSON file
            for (Object object : (JSONArray) _jsonParser.parse(reader)) { 
                database.add(_jParser.readObject((JSONObject) object));
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }  
        return database;
    }
    
    
    // Dump the list to the database
    public void update(List<T> objects) {
        JSONArray objectsList = new JSONArray();
        for (T genericObj : objects) {
            objectsList.add(_jParser.writeObject(genericObj));
        }
        //Write JSON file
        try (FileWriter file = new FileWriter(_path)) {
            file.write(objectsList.toJSONString());
            file.flush();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}