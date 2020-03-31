package com.app.seminar.controller;

import java.util.regex.Pattern;

import com.Context;
import com.app.controller.Controller;
import com.app.seminar.dbMapper.DbMapper;
import com.app.seminar.dbMapper.SeminarReader;
import com.app.seminar.model.Seminar;
import com.app.seminar.view.CSVSeminar;
import com.app.seminar.view.Layout;

public class CsvCourseController implements Controller{
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/course/csv/)[\\d]+$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
        
        String courseId = context.requestUri().replaceAll("\\D", "");
        
        
        final Seminar seminar = new DbMapper<Seminar>(context.connection(), new SeminarReader(), "Seminar").findById(courseId);
        
         context.response().getWriter().write(new Layout("CSV details", new CSVSeminar(seminar)).build().render());
    }

}
