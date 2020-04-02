package com.app.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.app.seminar.model.CSVDescription;
import com.app.seminar.model.Seminar;
import com.github.manliogit.javatags.element.Element;

public class CSVSeminar implements View  {
    private final Seminar _seminar;
    
    public CSVSeminar(Seminar seminar) {
        _seminar = seminar;
    }

    
    @Override
    public Element getHeader() {
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                h1(_seminar.getCourse().getCourseName())
              )
         );
    }
   
    @Override
    public Element[] getBody() {
        return _seminar.getDetails(new CSVDescription(_seminar));
    }
}
