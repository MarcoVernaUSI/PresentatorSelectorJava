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
    private final String path;
    private final JSONParser jsonParser = new JSONParser();
    private List<LogEntry> log;
    
    public Log(String path) {
        this.path = path;
        this.log = loadLog();
    }
    
 // Load the database into the list
    public List<LogEntry> loadLog() {       
        log = new ArrayList<>();
        
        try (FileReader reader = new FileReader(path))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            
            JSONArray entriesList = (JSONArray) obj;
            for (Object object : entriesList) {
                log.add(parseEntry((JSONObject) object));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
        return log;
    }
    
    // Parsing the json object entry in an LogEntry object
    private LogEntry parseEntry(JSONObject obj) {
        return new LogEntry((String) obj.get("name"), Action.valueOf((String) obj.get("action")), (String) obj.get("date"));
    }
    
    // Add an entry to the log
    public void saveEntry(Candidate speaker, Action action) {
        Date actualDate = new Date();
        LogEntry entry = new LogEntry(speaker.printCandidate(), action, actualDate);
        log.add(entry);
        updateLog();
    }
    
    // Dump the log to the json file
    public void updateLog() {
        JSONArray entriesList = new JSONArray();
        for (LogEntry entry : log) {
            JSONObject wentry = new JSONObject();
            wentry.put("name",entry.getSpeaker());
            wentry.put("action",entry.getAction().name());
            wentry.put("date",entry.getDate());
            entriesList.add(wentry);
        }
        //Write JSON file
        try (FileWriter file = new FileWriter(path)) {
            file.write(entriesList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Clear the log
    public void clearLog() {
        log.clear();
        updateLog();
    }
    
    // print the whole log
    public String printLog() {
        String logPrint = "";
        for (LogEntry entry : log) {
            logPrint = logPrint + "\n" + entry.getEntry(); 
        }
        return logPrint;
    }
}
