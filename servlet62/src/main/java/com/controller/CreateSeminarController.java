package com.controller;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.Context;
import com.dbMapper.SeminarMapper;
import com.model.Seminar;
import com.model.SeminarValidation;
import com.view.FormView;
import com.view.Layout;
import com.view.View;

public class CreateSeminarController implements Controller{
    String _route;

    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/course/create|/)$", route);
        
     //   return Pattern.matches("^(/course/)(create|/)?[\\d]?$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        context.response().getWriter().write(new Layout("Create Seminar", buildPage(context)).build().render());
    }
    
    public View buildPage(Context context) {
        Map<String,String> fields = Seminar.getFieldsTypes();
        Map<String,List<String>> errors = new SeminarValidation().validate(context.requestMap());
    
        if (context.post() && new SeminarValidation().isValid(context.requestMap())) {
             new SeminarMapper(context.connection()).insert(
                 new Seminar(context.requestMap()));   
             return SeminarListController.buildPage(context);        
            } 
        return new FormView("/course/create", fields, errors, context, context.requestMap());
    }
}
