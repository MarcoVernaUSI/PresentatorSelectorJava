package com.app.seminar.controller;

import java.util.regex.Pattern;

import com.Context;
import com.app.controller.Controller;
import com.app.seminar.model.mapper.StudentMapper;

public class StudentDeleteController implements Controller {
    
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/student/delete/)[\\d]+$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        String studentId = context.requestUri().replaceAll("\\D", "");
        new StudentMapper(context.connection()).delete(studentId);
        context.response().sendRedirect("/student");
    }
}
