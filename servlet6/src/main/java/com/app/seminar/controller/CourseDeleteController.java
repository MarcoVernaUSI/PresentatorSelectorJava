package com.app.seminar.controller;

import java.util.regex.Pattern;

import com.Context;
import com.app.controller.Controller;
import com.app.seminar.model.mapper.CourseMapper;

public class CourseDeleteController implements Controller {
    
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/course/delete/)[\\d]+$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        String courseId = context.requestUri().replaceAll("\\D", "");
        new CourseMapper(context.connection()).delete(courseId);
        context.response().sendRedirect("/");
    }
}
