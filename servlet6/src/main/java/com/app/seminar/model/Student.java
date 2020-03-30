package com.app.seminar.model;

import static java.util.Arrays.*;

import java.util.Map;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import com.app.seminar.model.rule.MaxLength;
import com.app.seminar.model.rule.NotEmpty;
import com.app.seminar.model.rule.Rule;

public class Student implements Entity {
    public static final String ID = "id";
    public static final String FIRST_NAME = "firstName";
    public static final String LAST_NAME = "lastName";
    
    private final Integer _id;
    private final String _firstName;
    private final String _lastName;

    public Student(Integer id, String firstName, String lastName) {
        _id = id;
        _firstName = firstName;
        _lastName = lastName;
    }
    
    public Student(Map<String, String> params) {
        this(
            params.get(ID).equals("") ? null : Integer.parseInt(params.get(ID)),
            params.get(FIRST_NAME),
            params.get(LAST_NAME)
         );
    }

    public String getFullName() {
        return _firstName + " " + _lastName;
    }

    public String getName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public Integer getId() {
        return _id;
    }
    
    public static MultiValuedMap<String, Rule> rules(){
        return new ArrayListValuedHashMap<String, Rule>(){{
           putAll("firstName",   asList(new NotEmpty(), new MaxLength(15)));
           putAll("lastName",    asList(new NotEmpty(), new MaxLength(20)));
       }};
   }
}
