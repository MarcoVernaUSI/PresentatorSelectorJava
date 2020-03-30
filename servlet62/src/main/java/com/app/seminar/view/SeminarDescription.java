package com.app.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.app.seminar.model.Seminar;
import com.github.manliogit.javatags.element.Element;

public class SeminarDescription implements View  {
    private final Seminar _seminar;
    
    public SeminarDescription(Seminar seminar) {
        _seminar = seminar;
    }
    
    @Override
    public Element build() {
        return text(_seminar.getDescription());
    }

}
