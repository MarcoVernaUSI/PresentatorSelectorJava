package main_package;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;  

public class Log extends JsonDb<String>{
    private final List<String> _database;
    public final static DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Log(String path) {
        super(path);
        _database = load();
    }
    
    @Override
    protected JSONObject writeObject(String entry) {
        JSONObject obj = new JSONObject();
        obj.put("entry",entry);
        return obj;
    }
    
    @Override
    protected String readObject(JSONObject obj) {
        return (String) obj.get("entry");
    }
    //////////////////////////
    
    
    // Add an entry to the log
    public void saveEntry(String speaker, boolean absent) {
        if (absent){
            Date actualDate = new Date();
            _database.add(printEntry(speaker,DateFormat.format(actualDate)));
            update(_database);
        }
    }
    
    // Clear the log
    public void clearLog() {
        _database.clear();
        update(_database);
    }
    
    // print the whole log
    public String printLog() {
        String logPrint = "";
        for (String entry : _database) {
            logPrint = logPrint + "\n" + entry; 
        }
        return logPrint;
    }
    
    private String printEntry(String speaker, String date) {
        return speaker + " absent in date " + date;
        
    }
    
    public int countEntries(){
        return _database.size();
    }
    
    public String getEntry(int i){
        return _database.get(i);
    }
}
