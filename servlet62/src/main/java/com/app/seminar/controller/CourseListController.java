package com.app.seminar.controller;

import static com.app.seminar.model.Seminar.*;
import static java.util.Arrays.*;

import java.util.regex.Pattern;

import com.Context;
import com.app.controller.Controller;
import com.app.seminar.dbMapper.DbMapper;
import com.app.seminar.dbMapper.SeminarReader;
import com.app.seminar.model.Seminar;
import com.app.seminar.view.Layout;
import com.app.seminar.view.SeminarList;

public class CourseListController implements Controller{
    

    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/course|/)$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
    
        Iterable<Seminar> seminars = new DbMapper<Seminar>(context.connection(), new SeminarReader(), "Seminar").findAll();
        //Iterable<String> header = asList(NAME, LOCATION, TOTAL_SEATS, START, "action");
        Iterable<String> header = asList(NAME, LOCATION, TOTAL_SEATS, START, "csv", "html");
        
        context.response().getWriter().write(new Layout("Seminars", new SeminarList(seminars, header)).build().render());
    
    }
    


}
