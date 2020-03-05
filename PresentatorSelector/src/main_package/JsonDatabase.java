package main_package;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// Class for interact with a json database
public class JsonDatabase {
    private String _path;
    private final JSONParser _jsonParser = new JSONParser();
    
    public JsonDatabase(String path) {
        this._path = path;
    }
    
    // Load the database into the list
    public List<JSONObject> load() {       
        List<JSONObject> database = new ArrayList<>();
        String currentPath = _path;

        try (FileReader reader = new FileReader(currentPath))
        {
            //Read JSON file
            Object obj = _jsonParser.parse(reader);
            
            JSONArray objectsList = (JSONArray) obj;
            for (Object object : objectsList) {
                
                database.add((JSONObject) object);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }  
        return database;
    }
  
    
 // Dump the list to the database
    public void update(JSONArray objectsList) {
        
        //Write JSON file
        try (FileWriter file = new FileWriter(_path)) {
            file.write(objectsList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
       
    public String getPath() {
        return _path;
    }

    public void setPath(String path) {
        this._path = path;
    }
}