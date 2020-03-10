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
public abstract class JsonDb<T> {
    private final String _path;
    protected final List<T> _database;
    private final JSONParser _jsonParser = new JSONParser();
    
    public JsonDb(String path) {
        _path = path;
        _database = new ArrayList<>();
        load();
    }
    
    public List<T> getDatabase() {
        return _database;
    }

    // Load the database into the list
    public List<T> load() {       
        String currentPath = _path;
        _database.clear();
        try (FileReader reader = new FileReader(currentPath))
        {
            //Read JSON file
            Object obj = _jsonParser.parse(reader);
            for (Object object : (JSONArray) obj) { 
                _database.add(readObject((JSONObject) object));
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }  
        return _database;
    }
    
 // Dump the list to the database
    public void update() {
        JSONArray objectsList = new JSONArray();
        for (T genericObj : _database) {
            JSONObject obj = writeObject(genericObj);
            objectsList.add(obj);
        }
        //Write JSON file
        try (FileWriter file = new FileWriter(_path)) {
            file.write(objectsList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    abstract public T readObject(JSONObject obj);
    
    abstract public JSONObject writeObject(T obj);
}