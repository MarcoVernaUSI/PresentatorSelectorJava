package com.app.seminar.model;

import static com.app.seminar.model.rule.Number.OPERATOR.*;
import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import com.app.seminar.model.rule.MaxLength;
import com.app.seminar.model.rule.NotEmpty;
import com.app.seminar.model.rule.Number;
import com.app.seminar.model.rule.Rule;

public class Course implements Entity {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String LOCATION = "location";
    public static final String TOTAL_SEATS = "totalSeats";
    public static final String START = "start";

    private final Integer _id;
    private final String _name;
    private final String _description;
    private final Integer _totalSeats;
    private final String _location;
    private final String _start;
    private final List<Student> _students = new ArrayList<>();

    public Course(Integer id, String name, String description, String location, Integer totalSeats,
        String courseDate) {
        _id = id;
        _name = name;
        _description = description;
        _location = location;
        _totalSeats = totalSeats;
        _start = courseDate;
    }

    public Course(Map<String, String> params) {
        this(
            params.get(ID).equals("") ? null : Integer.parseInt(params.get(ID)),
            params.get(NAME),
            params.get(DESCRIPTION),
            params.get(LOCATION),
            Integer.valueOf(params.get(TOTAL_SEATS)),
            params.get(START));
    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public String getStartDate() {
        return _start;
    }

    public Integer getId() {
        return _id;
    }

    public String getLocation() {
        return _location;
    }

    public void addStudent(Student student) {
        _students.add(student);
    }

    public Integer getTotalSeats() {
        return _totalSeats;
    }

    public Integer getSeatsLeft() {
        return getTotalSeats() - _students.size();
    }

    public static MultiValuedMap<String, Rule> rules() {
        return new ArrayListValuedHashMap<String, Rule>() {
            {
                putAll(NAME, asList(new NotEmpty(), new MaxLength(15)));
                putAll(LOCATION, asList(new NotEmpty(), new MaxLength(20)));
                putAll(TOTAL_SEATS,
                    asList(
                       new NotEmpty(),
                       new Number(GREATER_THAN, 0), 
                       new Number(LESS_THAN, 100), 
                       new MaxLength(3)
                    )
                );
            }
        };
    }
}
