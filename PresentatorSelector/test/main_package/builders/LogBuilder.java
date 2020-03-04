package main_package.builders;


import java.util.ArrayList;
import java.util.List;

import main_package.Action;
import main_package.Log;
import main_package.LogEntry;

public class LogBuilder {
    private static final String path = "test/log_test.json";
    private static final LogEntryBuilder _logEntryBuilder = new LogEntryBuilder();
    private static final List<LogEntry> logList = new ArrayList<LogEntry>() {{
        _logEntryBuilder.withSpeaker("Bob Semple").withAction(Action.ADDED).withDate("01/09/1939 00:00:00").build();
        _logEntryBuilder.withSpeaker("George Pearce").withAction(Action.REMOVED).withDate("02/11/1932 00:00:00").build();
    }};
    
    private final Log _log;
    
    
    public LogBuilder() {
        _log = new Log(path);
        _log.setLog(logList);   
    }
    
    public LogBuilder withPath(String path){
        _log.setPath(path); 
        return this;
    }
    
    public LogBuilder withEntries(LogEntry entry1,LogEntry entry2){
        List<LogEntry> _logList = new ArrayList<>();
        _logList.add(entry1);
        _logList.add(entry2);
        
        _log.setPath(path);
        _log.setLog(_logList);
        
        return this;
    }
    
    public Log build() {
        return _log;
    }
}

