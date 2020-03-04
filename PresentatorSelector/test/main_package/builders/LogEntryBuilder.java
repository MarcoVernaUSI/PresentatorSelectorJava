package main_package.builders;

import main_package.Action;
import main_package.LogEntry;

public class LogEntryBuilder {
    
    private static final String speaker = "Bob Semple";
    private static final Action action = Action.ADDED;
    private static final String stringDate = "01/09/1939 00:00:00"; ;
    
    private final LogEntry _logEntry;
    
    public LogEntryBuilder() {
        _logEntry = new LogEntry(speaker,action,stringDate);
    }
    
    public LogEntryBuilder withSpeaker(String speaker){
        _logEntry.setSpeaker(speaker);
        return this;
    }
    
    public LogEntryBuilder withAction(Action action){
        _logEntry.setAction(action);
        return this;
    }
    
    public LogEntryBuilder withDate(String date){
        _logEntry.setDate(date);
        return this;
    }
    
    public LogEntry build() {
        return _logEntry;
    }
}
