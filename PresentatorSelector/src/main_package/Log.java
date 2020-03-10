package main_package;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;  

public class Log{

    public final static DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final List<Entry<String,String>> _log;
    private JsonDatabase _db;
  
    public Log() {
        _log= new ArrayList<>();
    }

    public Log loadDatabase(JsonDatabase db){
        _db = db;
        _log.clear();
        for (JSONObject obj :  _db.load()) {
            _log.add(new java.util.AbstractMap.SimpleEntry<>
            ((String) obj.get("name"),(String) obj.get("date")));   
        }
        return this;
    }
    
    public void dumpDatabase(){
        JSONArray objectsList = new JSONArray();
        for (Entry<String,String> entry : _log) {
            JSONObject obj = new JSONObject();
            obj.put("name",entry.getKey());
            obj.put("date",entry.getValue());
            objectsList.add(obj);
        }
        _db.update(objectsList);
    }
    
    public void addEntry(String speaker, String date) {
        _log.add(new java.util.AbstractMap.SimpleEntry<>(speaker,date));   
    }
    
    // Add an entry to the log
    public void saveEntry(String speaker, boolean absent) {
        if (absent){
            Date actualDate = new Date();
            _log.add(new java.util.AbstractMap.SimpleEntry<>(speaker,DateFormat.format(actualDate)));
            dumpDatabase();
        }
    }
    
    // Clear the log
    public void clearLog() {
        _log.clear();
        dumpDatabase();
    }
    
    // print the whole log
    public String printLog() {
        String logPrint = "";
        for (Entry<String,String> entry : _log) {
            logPrint = logPrint + "\n" + printEntry(entry); 
        }
        return logPrint;
    }
    
    public String printEntry(Entry<String,String> entry) {
        return entry.getKey() + " absent in date " + entry.getValue();
        
    }
    
    public List<Entry<String,String>> getEntries() {
        return _log;
    }
}
