package main_package;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;  

public class Log extends JsonDb<String>{
    private static List<String> _database= new ArrayList<>();
    public final static DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Log(String path) {
        super(path, _database);
    }
    
    @Override
    public JSONObject writeObject(String entry) {
        JSONObject obj = new JSONObject();
        obj.put("entry",entry);
        return obj;
    }
    
    @Override
    public String readObject(JSONObject obj) {
        return (String) obj.get("entry");
    }
    //////////////////////////
    
    
    // Add an entry to the log
    public void saveEntry(String speaker, boolean absent) {
        if (absent){
            Date actualDate = new Date();
            _database.add(printEntry(speaker,DateFormat.format(actualDate)));
            update();
        }
    }
    
    // Clear the log
    public void clearLog() {
        _database.clear();
        update();
    }
    
    // print the whole log
    public String printLog() {
        String logPrint = "";
        for (String entry : _database) {
            logPrint = logPrint + "\n" + entry; 
        }
        return logPrint;
    }
    
    public String printEntry(String speaker, String date) {
        return speaker + " absent in date " + date;
        
    }
}
