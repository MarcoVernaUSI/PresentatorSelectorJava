package com.controller;

import static com.model.Seminar.*;
import static java.util.Arrays.*;

import java.util.regex.Pattern;

import com.Context;
import com.dbMapper.SeminarMapper;
import com.model.Seminar;
import com.view.Layout;
import com.view.SeminarList;

public class SeminarListController implements Controller{
    

    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/course|/)$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
      
        context.response().getWriter().write(new Layout("Seminars",buildPage(context)).build().render());
    }
    
    
    public static SeminarList buildPage(Context context) {
        Iterable<Seminar> seminars = new SeminarMapper(context.connection()).findAll();
        Iterable<String> header = asList(NAME, LOCATION, SEATS_LEFT, START, "csv", "html", "Delete");     
        
        return  new SeminarList(seminars, header);
    }
    


}
