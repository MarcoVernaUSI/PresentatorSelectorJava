package com.controller;

import java.util.regex.Pattern;

import com.Context;
import com.dbMapper.SeminarMapper;
import com.model.DetailsCSV;
import com.model.DetailsHTML;
import com.model.Seminar;
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
        Seminar seminar = new SeminarMapper(context.connection()).findById(seminarId);
        
        if (context.requestUri().contains("/csv/")) {
            DetailsCSV csv = new DetailsCSV(seminar);
            seminar.setDetails(csv);
            
            if (context.post()) {                
                    csv.writeCsvToFile();
            }
           
        
        }
        if (context.requestUri().contains("/html/")) {
        seminar.setDetails(new DetailsHTML(seminar));
        }
        
        
        
        
        context.response().getWriter().write(new Layout("Seminar details", new SeminarView(seminar)).build().render());
    }
    
}
