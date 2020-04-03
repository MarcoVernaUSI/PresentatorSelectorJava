package main_package;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;  

public class Log {
    public final static DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    private final List<String> _log;
    private final JsonDb<String> _db;

    
    public Log(String path) {
        _db = new JsonDb<>(path,new EntryParser());
        _log = _db.load();
    }
    
    
    public void saveEntry(String speaker, boolean absent) {
        if (absent){
            Date actualDate = new Date();
            _log.add(printEntry(speaker,DateFormat.format(actualDate)));
            _db.update(_log);
        }
    }
    

    public void clearLog() {
        _log.clear();
        _db.update(_log);
    }
    
    
    // print the whole log
    public String printLog() {
        String logPrint = "";
        for (String entry : _log) {
            logPrint = logPrint + "\n" + entry; 
        }
        return logPrint;
    }
    
    
    private String printEntry(String speaker, String date) {
        return speaker + " absent in date " + date;
    }
    
    
    public int countEntries(){
        return _log.size();
    }
    
    
    public String getEntry(int i){
        return _log.get(i);
    }
}
