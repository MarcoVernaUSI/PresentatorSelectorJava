package com.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.github.manliogit.javatags.element.Element;
import com.model.Seminar;

public class SeminarView implements View  {
 private final Seminar _seminar;
    
    public SeminarView(Seminar seminar) {
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
        return _seminar.getDetails();
    }

}
