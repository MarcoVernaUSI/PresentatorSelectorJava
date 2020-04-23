package com.controller;

import static com.model.Student.*;
import static java.util.Arrays.*;

import java.util.regex.Pattern;

import com.Context;
import com.dbMapper.StudentMapper;
import com.model.Student;
import com.view.Layout;
import com.view.StudentListView;

public class StudentsListController implements Controller{
    

    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/student|/)$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
      
        context.response().getWriter().write(new Layout("Students",buildPage(context)).build().render());
    }
    
    public static StudentListView buildPage(Context context) {
        Iterable<Student> seminars = new StudentMapper(context.connection()).findAll();
        Iterable<String> header = asList(FIRSTNAME, LASTNAME, "Delete");     
        
        return  new StudentListView(seminars, header);
    }
}
