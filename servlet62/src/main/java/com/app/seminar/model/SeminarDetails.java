package com.app.seminar.model;

public abstract class SeminarDetails {
    
    private final Seminar _seminar;
    
    public SeminarDetails(Seminar seminar) {
        _seminar = seminar;
    }
    
    protected abstract String getHeader();

    protected abstract String getBody();
    
    protected abstract String getFooter();
    
    public String print() {
        return getHeader()+getBody()+getFooter();  
    }

    protected Seminar getSeminar() {
        return _seminar;
    }
    
}
