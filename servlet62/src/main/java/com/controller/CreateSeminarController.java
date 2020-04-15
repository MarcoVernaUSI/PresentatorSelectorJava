package com.controller;

import static com.model.Seminar.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.Context;
import com.dbMapper.SeminarMapper;
import com.model.Seminar;
import com.view.Feedback;
import com.view.FormView;
import com.view.Layout;
import com.view.View;

public class CreateSeminarController implements Controller{

    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/course/create|/)$", route);
        
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
        
        context.response().getWriter().write(new Layout("Create Seminar", buildPage(context)).build().render());

        
        
    }
    
    private boolean validate(Map<String,String> map) {
        
        if(!map.get(LOCATION).equals("")
            && Integer.parseInt(map.get(TOTAL_SEATS)) > 0
            && !map.get(NAME).equals("")
            && !map.get(DESCRIPTION).equals("")
            && !map.get(START).equals("")
            ) {
            return true;
        }else {
            return false;
        }
    }

    
    public View buildPage(Context context) {
        Feedback result = Feedback.DEFAULT;
        
        Map<String,String> fields = new HashMap<String, String>(){
            {
                put(NAME, "text");
                put(DESCRIPTION, "text");
                put(LOCATION, "text");
                put(TOTAL_SEATS, "number");
                put(START, "date");
            } 
         };
         
         
        if (context.post()) {
           if (validate(context.requestMap())) {
                new SeminarMapper(context.connection()).insert(new Seminar(context.requestMap()));
                result = Feedback.SUCCESS;
                return SeminarListController.buildPage(context);
            } else {
                result = Feedback.ERROR;
            }
        } 
        return new FormView("/course/create", fields, result);
    }


}
