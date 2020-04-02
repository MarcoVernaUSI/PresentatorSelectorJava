package com.model;

public class Course { 
    
    private final String _courseName;
    private final String _description;
    
    public Course(String courseName, String description) {
        _courseName = courseName;
        _description = description;
    }

    public String getCourseName() {
        return _courseName;
    }

    public String getDescription() {
        return _description;
    }
}
