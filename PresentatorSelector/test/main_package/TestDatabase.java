package main_package;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TestDatabase {

    public final String _path;
    
    public TestDatabase(String path) {
        _path = path;
    }

    public void add(String jsonKey, String value) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put(jsonKey,value);
        JSONArray objectsList = new JSONArray();
        objectsList.add(jsonObj);
        try (FileWriter file = new FileWriter(_path)) {
            file.write(objectsList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
