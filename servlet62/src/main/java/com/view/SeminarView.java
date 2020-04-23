package com.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.github.manliogit.javatags.element.Element;
import com.model.Seminar;

public class SeminarView implements View  {
 private final Seminar _seminar;
    
    

    public SeminarView(Seminar seminar) {
        _seminar = seminar;
    }

    
    public Element getHeader() {
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                h2(_seminar.getName())
              )
         );
    }
   
    @Override
    public Element[] getBody() {
        Element[] elements = new Element[_seminar.getDetails().size()+1];
        elements[0]=getHeader();
        for (int i = 1; i<_seminar.getDetails().size()+1; i++) {
            elements[i]=_seminar.getDetails().get(i-1);
        }
        return elements;
    }
    
    
    public Seminar getContent() {
        return _seminar;
    }

}
