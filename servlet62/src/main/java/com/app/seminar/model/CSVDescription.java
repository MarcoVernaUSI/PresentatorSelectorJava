package com.app.seminar.model;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;

public class CSVDescription implements SeminarDescription{
    private final Seminar _seminar;


    public CSVDescription(Seminar seminar) {
        _seminar = seminar;
    }

    protected String getBody2() {
        String body ="";
        // Body        
        for (Student partecipant: _seminar.getStudentsList()) {
            body+=("\""+partecipant.getName()+ "\";\""+partecipant.getSurname()+"\"\n");
        }
        return body;
    }

    protected Element getHeader() {
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                h1(_seminar.getCourse().getCourseName())
              )
         );
    }
    
    
    private Element[] studentList() {
        List<Element> rows = new ArrayList<Element>();
        for (Student student : _seminar.getStudentsList()) {
            rows.add(text("\""+student.getName()+ "\";\""+student.getSurname()+"\"\n"));
        }
        Element[] elements= rows.toArray(new Element[rows.size()]);
        return elements;
    }

    protected Element getBody() {
        return text("\""+_seminar.getId()+"\";\""+_seminar.getName()+"\";\""+_seminar.getStartDate()+"\";\""+_seminar.getLocation()+"\";\""+_seminar.getSeatLeft()+"\"\n");
    }
    
    @Override
    public Element[] getDetails() {
        Element[] elements = new Element[studentList().length+2];
        elements[0]=getHeader();
        elements[1]=getBody();
        for (int i = 2; i<studentList().length+2; i++) {
            elements[i]=studentList()[i-2];
        }
        return elements;
    }

}
