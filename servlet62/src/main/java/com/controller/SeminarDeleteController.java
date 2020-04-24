package com.controller;

import java.util.regex.Pattern;

import com.Context;
import com.dbMapper.SeminarMapper;
import com.view.Layout;
import com.view.View;

public class SeminarDeleteController implements Controller{

    
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/course/delete/)[\\d]+$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
        
        context.response().getWriter().write(new Layout("Seminars", buildPage(context)).build().render());
    }

    public View buildPage(Context context) {
        String seminarId = context.requestUri().replaceAll("\\D", "");
     //   if (new SeminarMapper(context.connection()).findById(seminarId).getStudentsList().size() == 0) {
            new SeminarMapper(context.connection()).delete(seminarId);
     //   }
        
        return SeminarListController.buildPage(context);
    }
    
}
