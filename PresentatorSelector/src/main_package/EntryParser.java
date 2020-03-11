package main_package;

import org.json.simple.JSONObject;

public class EntryParser implements JsonObjectParser<String>{
    @Override
    public String readObject(JSONObject obj){
        return (String) obj.get("entry");
    }    
    
    @Override
    public JSONObject writeObject(String  entry){
        JSONObject obj = new JSONObject();
        obj.put("entry",entry);
        return obj;
    }
}
