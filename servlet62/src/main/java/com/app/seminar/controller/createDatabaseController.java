package com.app.seminar.controller;

import static com.app.seminar.model.Seminar.*;
import static java.util.Arrays.*;

import java.util.regex.Pattern;

import com.Context;
import com.app.controller.Controller;
import com.app.seminar.dbMapper.DbMapper;
import com.app.seminar.dbMapper.SeminarReader;
import com.app.seminar.dbMapper.StudentReader;
import com.app.seminar.model.Seminar;
import com.app.seminar.model.Student;
import com.app.seminar.view.Layout;
import com.app.seminar.view.SeminarList;

public class createDatabaseController implements Controller{

    @Override
    public boolean handles(String route) {
        return Pattern.matches("/create", route);
        
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
    
        Iterable<Seminar> seminars = new DbMapper<Seminar>(context.connection(), new SeminarReader()).findAll();
        //Iterable<String> header = asList(NAME, LOCATION, TOTAL_SEATS, START, "action");
        Iterable<String> header = asList(NAME, LOCATION, TOTAL_SEATS, START, "csv", "html");
        
        context.response().getWriter().write(new Layout("Seminars", new SeminarList(seminars, header)).build().render());
    }
    
    
    public void saveStudent(Context context, Student student) {
        new DbMapper<Student>(context.connection(), new StudentReader()).save(student);
        context.response();
    }

    public void saveCourse(Context context, Seminar seminar) {
        new DbMapper<Seminar>(context.connection(), new SeminarReader()).save(seminar);
            context.response();
          }
}
