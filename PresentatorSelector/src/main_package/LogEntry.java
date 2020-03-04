package main_package;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date; 


public class LogEntry {
    private String speaker;
    private Action action;
    private Date date;
    private final DateFormat dateFormat;

    public LogEntry(String speaker, Action action, String date) {
        super();
        this.speaker = speaker;
        this.action = action;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public LogEntry(String speaker, Action action, Date date) {
        super();
        this.speaker = speaker;
        this.action = action;
        this.date = date;
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");     
    }
    
    public String getSpeaker() {
        return speaker;
    }
    
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public Action getAction() {
        return action;
    }
    
    public void setAction(Action action) {
        this.action = action;
    }

    public String getDate() {
        return this.dateFormat.format(this.date);
    }
    
    public void setDate(String date) {
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEntry() {
        String entry = speaker + " " + this.action.name() + " in date " + this.dateFormat.format(this.date);
        return entry;
    }

}
