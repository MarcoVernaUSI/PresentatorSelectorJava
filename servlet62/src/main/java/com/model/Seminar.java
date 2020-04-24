package com.model;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.manliogit.javatags.element.Element;

public class Seminar{
    
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String LOCATION = "location";
    public static final String SEATS_LEFT = "Seats left";
    public static final String TOTAL_SEATS = "totalSeats";
    public static final String START = "start date";
    
    private final String _location;
    private final int _totalSeats;
    private final String _courseName;
    private final String _description;
    private final List<Student> _enrollments;
    private final String _startDate;
    private final int _id;
    private Details _details;

    public Seminar(int id, String location, int totalSeats, String courseName, String courseDescription, String startDate) {
        _id = id;
        _location = location;
        _totalSeats = totalSeats;
        _courseName = courseName;
        _description = courseDescription;
        _startDate = startDate;
        
        _enrollments = new ArrayList<Student>();
    }
    
    public Seminar(Map<String, String> inputs){
        this(
            -1,
            inputs.get(LOCATION),
            Integer.parseInt(inputs.get(TOTAL_SEATS)),
            inputs.get(NAME),
            inputs.get(DESCRIPTION),
            inputs.get(START)
            );
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
        return _courseName;
    }

    public String getDescription() {
        return _description;
    }

    public String getStartDate() {
        return _startDate;
    }
    
    public String getFormattedDate() {
        String date = _startDate.replaceAll("\\D", ".");
        String year = date.substring(0, date.length()-6);
        return date.substring(date.length()-5)+"."+year;
    }

    public int getId() {
        return _id;
    }
    
    public int getTotalSeats() {
        return _totalSeats;
    }

    public List<Element> getDetails() {
        return _details.print();
    }

    public void setDetails(Details details) {
        _details = details;
    }
    
    public static Map<String,String> getFieldsTypes(){
        Map<String,String> fields = new HashMap<String, String>();
        fields.put(NAME, "text");
        fields.put(DESCRIPTION, "text");
        fields.put(LOCATION, "text");
        fields.put(TOTAL_SEATS, "number");
        fields.put(START, "date");
        return fields;
    }
    
    public Map<String,String> getFieldsValues(){
        Map<String,String> fields = new HashMap<String, String>();
        fields.put(NAME, getName());
        fields.put(DESCRIPTION, getDescription());
        fields.put(LOCATION, getLocation());
        fields.put(TOTAL_SEATS, String.valueOf((getTotalSeats())));
        fields.put(START, getStartDate());
        return fields;
    }
    
    public static Iterable<String> getFieldList(){
        return asList(NAME, LOCATION, SEATS_LEFT, START);
    }
}


