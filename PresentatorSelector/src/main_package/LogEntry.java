package main_package;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date; 


public class LogEntry {
    private String speaker;
    private Date date;
    public final static DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public LogEntry(String speaker, String date) {
        super();
        this.speaker = speaker;
        try {
            this.date = DateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public LogEntry(String speaker, Date date) {
        super();
        this.speaker = speaker;
        this.date = date;
    }
    
    public String getSpeaker() {
        return speaker;
    }
    
    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getDate() {
        return DateFormat.format(this.date);
    }
    
    public void setDate(String date) {
        try {
            this.date = DateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getEntry() {
        String entry = speaker + " absent in date " + DateFormat.format(this.date);
        return entry;
    }

}
