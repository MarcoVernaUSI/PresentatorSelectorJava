package com.app.seminar.controller;

import static com.app.seminar.model.Course.*;
import static java.util.Arrays.*;

import java.util.regex.Pattern;

import com.Context;
import com.app.controller.Controller;
import com.app.seminar.model.Course;
import com.app.seminar.model.mapper.CourseMapper;
import com.app.seminar.view.CourseComponentFactory;
import com.app.seminar.view.Layout;
import com.app.seminar.view.Table;

public class CourseListController implements Controller {
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/course|/)$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
      
        Iterable<Course> courses = new CourseMapper(context.connection()).findAll();
        Iterable<String> header = asList(NAME, LOCATION, TOTAL_SEATS, START, "action");
        
        context.response().getWriter().write(new Layout("courses", new Table(courses, header, new CourseComponentFactory())).build().render());
    }
}
