package com.app.seminar.controller;

import static com.app.seminar.model.Student.*;
import static java.util.Arrays.*;

import java.util.regex.Pattern;

import com.Context;
import com.app.controller.Controller;
import com.app.seminar.model.Student;
import com.app.seminar.model.mapper.StudentMapper;
import com.app.seminar.view.Layout;
import com.app.seminar.view.StudentComponentFactory;
import com.app.seminar.view.Table;

public class StudentListController implements Controller {
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/student)$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
      
        Iterable<Student> students = new StudentMapper(context.connection()).findAll();
        Iterable<String> header = asList(FIRST_NAME, LAST_NAME, "action");
        
        context.response().getWriter().write(new Layout("students", new Table(students, header, new StudentComponentFactory())).build().render());
    }
}
