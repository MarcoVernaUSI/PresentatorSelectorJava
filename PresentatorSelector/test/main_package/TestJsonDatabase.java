package main_package;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main_package.builders.JsonDatabaseBuilder;


public class TestJsonDatabase {
    public static final String Path = JsonDatabaseBuilder.DefaultPath;
    public JsonDatabaseBuilder _db;
    
    @Before
    public void setUp() {
        _db = new JsonDatabaseBuilder().ofCandidates().writeFile();
    }
    
    // Cancello file
    @After
    public void rollback(){
        File jsonFile = new File(Path);
        jsonFile.delete();
    }
    
    // READ FILE TEST
    @Test
    public void loadDatabase() {
       JsonDatabase db = _db.build();
       List<JSONObject> objectsList =db.getDatabase();
       
       List<JSONObject> objList=db.load();
        
       assertEquals(objectsList.get(0).get("fname"), objList.get(0).get("fname"));
       assertEquals(objectsList.get(0).get("surname"), objList.get(0).get("surname"));
       assertEquals(objectsList.get(1).get("fname"), objList.get(1).get("fname"));
       assertEquals(objectsList.get(1).get("surname"), objList.get(1).get("surname"));  
     }
    
    // WRITE FILE TEST
    @Test
    public void updateDatabase() {
        JsonDatabase db = _db.build();
        List<JSONObject> objectsList =db.getDatabase();
        // lo preparo per scrittura
        JSONArray tmpList = new JSONArray();
        tmpList.add(objectsList.get(0));
        tmpList.add(objectsList.get(1));
        
        db.update(tmpList);
        
        List<JSONObject> readedDatabase = _db.readDb();
            
        assertEquals(objectsList.get(0).get("fname"), readedDatabase.get(0).get("fname"));
        assertEquals(objectsList.get(0).get("surname"), readedDatabase.get(0).get("surname"));
        assertEquals(objectsList.get(1).get("fname"), readedDatabase.get(1).get("fname"));
        assertEquals(objectsList.get(1).get("surname"), readedDatabase.get(1).get("surname"));  
    }

}
