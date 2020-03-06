package main_package;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;  

public class Log implements dbInterface{

    private final JsonDatabase _db;
    private final List<LogEntry> _log = new ArrayList<>();

    public Log(JsonDatabase db) {
        _db = db;
        loadDatabase();
    }
    
    @Override
    public void loadDatabase(){
        for (JSONObject obj :  _db.load()) {
            _log.add(new LogEntry((String) obj.get("name"), (String) obj.get("date")));   
        }  
    }
    
    @Override
    public void dumpDatabase(){
        JSONArray objectsList = new JSONArray();
        for (LogEntry entry : _log) {
            JSONObject obj = new JSONObject();
            obj.put("name",entry.getSpeaker());
            obj.put("date",entry.getDate());
            objectsList.add(obj);
        }
        _db.update(objectsList);
    }
    
    // Add an entry to the log
    public void saveEntry(String speaker, boolean absent) {
        if (absent){
            Date actualDate = new Date();
            LogEntry entry = new LogEntry(speaker, actualDate);
            _log.add(entry);
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
        for (LogEntry entry : _log) {
            logPrint = logPrint + "\n" + entry.getEntry(); 
        }
        return logPrint;
    }
    
    public List<LogEntry> getEntries() {
        return _log;
    }
}
