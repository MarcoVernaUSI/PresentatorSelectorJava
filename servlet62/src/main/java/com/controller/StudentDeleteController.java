package com.controller;

import java.util.regex.Pattern;

import com.Context;
import com.dbMapper.StudentMapper;
import com.view.Layout;
import com.view.View;

public class StudentDeleteController implements Controller{

    
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/student/delete/)[\\d]+$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
        
        context.response().getWriter().write(new Layout("Students", buildPage(context)).build().render());
    }

    public View buildPage(Context context) {
        String studentId = context.requestUri().replaceAll("\\D", "");
     //   if (!(new StudentMapper(context.connection()).isEnrolled(studentId))) {
            new StudentMapper(context.connection()).delete(studentId);
     //   }
        
        return StudentsListController.buildPage(context);
    }
    
}
