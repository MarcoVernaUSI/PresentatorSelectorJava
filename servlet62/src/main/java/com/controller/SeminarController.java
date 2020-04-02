package com.controller;

import java.util.regex.Pattern;

import com.Context;
import com.app.seminar.dbMapper.SeminarMapper;
import com.model.SeminarHTML;
import com.view.Layout;
import com.view.SeminarView;

public class SeminarController implements Controller{

    
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/course/)(html/)?(csv/)?[\\d]+$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
        
        String seminarId = context.requestUri().replaceAll("\\D", "");
        
        
        //if (context.requestUri().contains("/csv/")) {
         //   SeminarCSV seminar = (SeminarCSV) new SeminarMapper(context.connection()).findById(seminarId);
         //   context.response().getWriter().write(new Layout("Html details", new SeminarView(seminar)).build().render());
            
        //}
//if (context.requestUri().contains("/html/")) {
        //else {
            SeminarHTML seminar = (SeminarHTML) new SeminarMapper(context.connection()).findById(seminarId);
            context.response().getWriter().write(new Layout("Html details", new SeminarView(seminar)).build().render());
            
        //}
        
    }
    
}
