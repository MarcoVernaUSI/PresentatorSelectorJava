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

public class UpdateController implements Controller{

    
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/course/)[\\d]+$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        
        context.response().getWriter().write(
            new Layout("Seminar", buildPage(context)).build().render());
    }

    public View buildPage(Context context) {
        String seminarId = context.requestUri().replaceAll("\\D", "");
        Map<String,String> defaultFields = new SeminarMapper(context.connection()).findById(seminarId).getFieldsValues();
        
        
        Map<String,String> fields = Seminar.getFields();
        Map<String,List<String>> errors = SeminarValidation.validate(context.requestMap());
    
        if (context.post()) {
            defaultFields = context.requestMap();
            
            if (SeminarValidation.isValid(context.requestMap())) {
                new SeminarMapper(context.connection()).update(
                    new Seminar(context.requestMap()), seminarId);   
                    return SeminarListController.buildPage(context);
                }
        }
         
        return new FormView("/course/"+seminarId, fields, errors, context, defaultFields);
    }
}
