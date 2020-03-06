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
    private final List<JSONObject> _database;

    private final JSONParser _jsonParser = new JSONParser();
    
    public JsonDatabase(String path) {
        _path = path;
        _database = new ArrayList<>();
    }
    
    // Load the database into the list
    public List<JSONObject> load() {       
        String currentPath = _path;
        _database.clear();

        try (FileReader reader = new FileReader(currentPath))
        {
            //Read JSON file
            Object obj = _jsonParser.parse(reader);
            for (Object object : (JSONArray) obj) {
                _database.add((JSONObject) object);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }  
        return _database;
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
    
    public List<JSONObject> getDatabase() {
        return _database;
    }

    public void addToDatabase(JSONObject obj) {
        _database.add(obj);
    }
}