package com.model;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;

public class DetailsHTML implements Details{
    
    private final Seminar _seminar;
    
    public DetailsHTML(Seminar seminar) {
        _seminar = seminar;
    }
    
    private Element getHeader() {
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                h1(_seminar.getCourse().getCourseName())
              )
            );}
    
    
    private Element[] studentList() {
        List<Element> rows = new ArrayList<Element>();
        for (Student student : _seminar.getStudentsList()) {
            rows.add(text("\""+student.getName()+ "\";\""+student.getSurname()+"\"\n"));
        }
        Element[] elements= rows.toArray(new Element[rows.size()]);
        return elements;
    }
    
    private Element getBody() {
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-8 col-sm-9"),
                p(attr("class -> lead"),
                    text(_seminar.getStartDate())
                  ),
                p(attr("class -> lead"),
                    text(_seminar.getLocation())
                  ),
                p(attr("class -> lead"),
                    text(String.valueOf(_seminar.getSeatLeft()))
                  ),
                p(attr("class -> lead"),
                    text(_seminar.getDescription())
                  ),
                p(attr(),
                text(String.valueOf(_seminar.getStudentsList().size()))
                )
            )
          );
    }

    @Override
    public Element[] print() {
        Element[] elements = new Element[studentList().length+2];
        elements[0]=getHeader();
        elements[1]=getBody();
        for (int i = 2; i<studentList().length+2; i++) {
            elements[i]=studentList()[i-2];
        }
        return elements;
    }
}