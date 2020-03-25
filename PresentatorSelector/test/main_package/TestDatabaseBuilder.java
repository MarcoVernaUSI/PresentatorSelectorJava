package main_package;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class TestDatabaseBuilder {

    public final String _path;
    
    public TestDatabaseBuilder(String path) {
        _path = path;
    }

    public void createDb(String jsonKey, String value) {
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
