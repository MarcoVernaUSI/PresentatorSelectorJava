package com.controller;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.Context;
import com.dbMapper.StudentMapper;
import com.model.Student;
import com.model.StudentValidation;
import com.view.FormView;
import com.view.Layout;
import com.view.View;

public class CreateStudentController implements Controller{

    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/student/create|/)$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        context.response().getWriter().write(new Layout("Create Seminar", buildPage(context)).build().render());
    }
    
    public View buildPage(Context context) {
        Map<String,String> fields = Student.getFieldsTypes();
        Map<String,List<String>> errors = new StudentValidation().validate(context.requestMap());
        
        
        if (context.post() && new StudentValidation().isValid(context.requestMap())) {
             new StudentMapper(context.connection()).insert(
                 new Student(context.requestMap()));   
             return StudentsListController.buildPage(context);        
            } 
        return new FormView("/student/create", fields, errors, context, context.requestMap());
    }
}
