package com.app.seminar.model;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;

public class Seminar {
    
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String LOCATION = "location";
    public static final String TOTAL_SEATS = "totalSeats";
    public static final String START = "start";
    
    private final String _location;
    private final int _totalSeats;
    private final Course _course;
    private final List<Student> _enrollments;
    private final String _startDate;
    private final int _number;

    public Seminar(int number, String location, int totalSeats, Course course, String startDate) {
        _location = location;
        _totalSeats = totalSeats;
        _course = course;
        _startDate = startDate;
        _enrollments = new ArrayList<Student>();
        _number = number;
    }
    
    public Element[] getDetails(SeminarDescription type) {
        return type.getDetails();     
    }
    
    public void addEnrollment(Student enrollment) {
        _enrollments.add(enrollment);
    }

    public int getSeatLeft() {
        return _totalSeats - _enrollments.size();
    }

    public String getLocation() {
        return _location;
    }

    public List<Student> getStudentsList() {
        return _enrollments;
    }

    public String getName() {
        return _course.getCourseName();
    }

    public Course getCourse() {
        return _course;
    }
    
    public String getDescription() {
        return _course.getDescription();
    }

    public String getStartDate() {
        return _startDate;
    }
    

    public int getId() {
        return _number;
    }
    
    public int getTotalSeats() {
        return _totalSeats;
    }
}


