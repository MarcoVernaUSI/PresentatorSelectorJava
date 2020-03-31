package com.app.seminar.model;

public class Student {
    private final int _id;
    private final String _name;
    private final String _surname;
    
    public Student(int id, String name, String surname) {
        _id = id;
        _name = name;
        _surname = surname;
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
}
