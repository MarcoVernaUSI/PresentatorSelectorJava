package main_package.builders;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import main_package.JsonDatabase;

public class FakeJsonDatabase {
    public static final String DefaultPath = "test/database_test.json";
    public final JsonDatabase _db;
    
    public FakeJsonDatabase() {
        _db = new JsonDatabase(DefaultPath); 
    }
    
    public FakeJsonDatabase withPath(String path) {
        _db.setPath(path);
        return this;
    }
    
    public FakeJsonDatabase writeFile() {
        try (FileWriter file = new FileWriter(_db.getPath())) {
            
            JSONArray tmpList = new JSONArray();
            for (Object obj : _db.getDatabase()) {
                tmpList.add(obj);
            }
            file.write(tmpList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }     
        return this;
    }
    
    public FakeJsonDatabase ofCandidates() {
        JSONObject obj1 = new JSONObject();
        obj1.put("fname","Bob");
        obj1.put("surname","Semple");
        
        JSONObject obj2 = new JSONObject();
        obj2.put("fname","George");
        obj2.put("surname","Pearce");
        
        _db.addToDatabase(obj1);
        _db.addToDatabase(obj2);
        return this;
    }
    
    public FakeJsonDatabase ofLogEntries() {
        JSONObject obj1 = new JSONObject();
        obj1.put("name","Bob Semple");
        obj1.put("date","01/09/1939 00:00:00");
        
        JSONObject obj2 = new JSONObject();
        obj2.put("name","George Pearce");
        obj2.put("date","01/11/1942 00:00:00");
      
        _db.addToDatabase(obj1);
        _db.addToDatabase(obj2);
        return this;
    }
    
    public List<JSONObject> readDb() {
        List<JSONObject> readedDatabase = new ArrayList<>();
        try (FileReader reader = new FileReader(_db.getPath())) {
            //Read JSON file
            Object obj = new JSONParser().parse(reader);
            JSONArray readedObject = (JSONArray) obj;
            for (Object object : readedObject) {
                readedDatabase.add((JSONObject) object);
            }
            
            return readedDatabase;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public JsonDatabase build() {
        return _db;
    }

    
}

