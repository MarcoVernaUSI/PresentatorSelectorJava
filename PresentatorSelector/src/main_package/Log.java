package main_package;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;  

public class Log {
    private final String path;
    private final JSONParser jsonParser = new JSONParser();
    private ArrayList<LogEntry> log;
    
    public Log(String path) {
        super();
        this.path = path;
        this.log = this.load_log();
    }
    
 // Load the database into the list
    public ArrayList<LogEntry> load_log() {       
        this.log = new ArrayList<>();
        
        try (FileReader reader = new FileReader(this.path))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            
            JSONArray entriesList = (JSONArray) obj;
            for (Object object : entriesList) {
                log.add(parseEntry((JSONObject) object));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        
        return log;
    }
    
    // Parsing the json object entry in an LogEntry object
    private LogEntry parseEntry(JSONObject obj) {
        return new LogEntry((String) obj.get("name"), Action.valueOf((String) obj.get("action")), (String) obj.get("date"));
    }
    
    // Add an entry to the log
    public void save_entry(Candidate speaker, Action action) {
        Date actual_date = new Date();
        LogEntry entry = new LogEntry(speaker.printCandidate(), action, actual_date);
        this.log.add(entry);
        this.update_log();
    }
    
    // Dump the log to the json file
    public void update_log() {
        JSONArray entries_list = new JSONArray();
        for (LogEntry entry : this.log) {
            JSONObject wentry = new JSONObject();
            wentry.put("name",entry.getSpeaker());
            wentry.put("action",entry.getAction().name());
            wentry.put("date",entry.getDate());
            entries_list.add(wentry);
        }
        //Write JSON file
        try (FileWriter file = new FileWriter(this.path)) {
            file.write(entries_list.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Clear the log
    public void clear_log() {
        this.log.clear();
        this.update_log();
    }
    
    // print the whole log
    public String print_log() {
        String log_print = "";
        for (LogEntry entry : this.log) {
            log_print = log_print + "\n" + entry.getEntry(); 
        }
        return log_print;
    }
        

}
