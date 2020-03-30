package com.app.seminar.model;

public class Course { 
    
    private final String _courseName;
    private final int _number;
    private final String _description;
    
    public Course(String courseName, int number, String description) {
        _courseName = courseName;
        _number = number;
        _description = description;
    }

    public String getCourseName() {
        return _courseName;
    }

    public int getNumber() {
        return _number;
    }

    public String getDescription() {
        return _description;
    }
}
