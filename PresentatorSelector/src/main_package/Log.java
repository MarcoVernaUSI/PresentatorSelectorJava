package main_package;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;  

public class Log {
    private final String _path;
    private final JSONParser _jsonParser = new JSONParser();
    private List<LogEntry> _log;
    
    public Log(String path) {
        super();
        _path = path;
        _log = loadLog();
    }
    
 // Load the database into the list
    public List<LogEntry> loadLog() {       
        _log = new ArrayList<>();
        
        try (FileReader reader = new FileReader(_path))
        {
            //Read JSON file
            Object obj = _jsonParser.parse(reader);
            
            JSONArray entriesList = (JSONArray) obj;
            for (Object object : entriesList) {
                _log.add(parseEntry((JSONObject) object));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
        
        return _log;
    }
    
    // Parsing the json object entry in an LogEntry object
    private LogEntry parseEntry(JSONObject obj) {
        return new LogEntry((String) obj.get("name"), Action.valueOf((String) obj.get("action")), (String) obj.get("date"));
    }
    
    // Add an entry to the log
    public void save_entry(Candidate speaker, Action action) {
        Date actual_date = new Date();
        LogEntry entry = new LogEntry(speaker.printCandidate(), action, actual_date);
        _log.add(entry);
        update_log();
    }
    
    // Dump the log to the json file
    public void update_log() {
        JSONArray entries_list = new JSONArray();
        for (LogEntry entry : _log) {
            JSONObject wentry = new JSONObject();
            wentry.put("name",entry.getSpeaker());
            wentry.put("action",entry.getAction().name());
            wentry.put("date",entry.getDate());
            entries_list.add(wentry);
        }
        //Write JSON file
        try (FileWriter file = new FileWriter(_path)) {
            file.write(entries_list.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Clear the log
    public void clear_log() {
        _log.clear();
        update_log();
    }
    
    // print the whole log
    public String print_log() {
        String log_print = "";
        for (LogEntry entry : _log) {
            log_print = log_print + "\n" + entry.getEntry(); 
        }
        return log_print;
    }
}
