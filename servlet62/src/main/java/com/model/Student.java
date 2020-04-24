package com.model;

import static java.util.Arrays.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student{
    public static final String ID = "id";
    public static final String FIRSTNAME = "firstName";
    public static final String LASTNAME = "lastName";
   
    
    private final int _id;
    private final String _name;
    private final String _surname;
    
    public Student(int id, String name, String surname) {
        _id = id;
        _name = name;
        _surname = surname;
    }

    public Student(Map<String, String> inputs) {
        this(
            -1,
            inputs.get(FIRSTNAME),
            inputs.get(LASTNAME)
            );
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getSurname() {
        return _surname;
    }
    
    public static Map<String,String> getFieldsTypes(){
        Map<String,String> fields = new HashMap<String, String>();
        fields.put(FIRSTNAME, "text");
        fields.put(LASTNAME, "text");
        return fields;
    }

    public Map<String,String> getFieldsValues(){
        Map<String,String> fields = new HashMap<String, String>();
        fields.put(FIRSTNAME, getName());
        fields.put(LASTNAME, getSurname());
        return fields;
    }
    
    public static List<String> getFieldList(){
        return asList(FIRSTNAME, LASTNAME);     
    }  
}
