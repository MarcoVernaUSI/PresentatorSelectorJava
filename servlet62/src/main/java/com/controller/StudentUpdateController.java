package com.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.Context;
import com.dbMapper.StudentMapper;
import com.model.Student;
import com.view.FormView;
import com.view.Layout;
import com.view.View;

public class StudentUpdateController implements Controller{

    
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/student/)[\\d]+$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        
        context.response().getWriter().write(
            new Layout("Student", buildPage(context)).build().render());
    }

    public View buildPage(Context context) {
        String studentId = context.requestUri().replaceAll("\\D", "");
        Map<String,String> defaultFields = new StudentMapper(context.connection()).findById(studentId).getFieldsValues();
        
        
        Map<String,String> fields = Student.getFieldsTypes();
       
        if (context.post()) {
            defaultFields = context.requestMap();
            
            new StudentMapper(context.connection()).update(
                new Student(context.requestMap()), studentId);   
                return StudentsListController.buildPage(context);
            
        }
         
        return new FormView("/student/"+studentId, fields, Collections.<String, List<String>>emptyMap(), context, defaultFields);
    }
}
