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
    
    
    private Element buildTable() {        
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-8 col-sm-9"),
                table(attr("class -> table table-striped"), TableBody())
            )
        );
    }
    
    private Element TableBody() {
        List<Element> rows = new ArrayList<Element>();
        rows.add(buildRow("Nome",_seminar.getName()));
        rows.add(buildRow("Luogo",_seminar.getLocation()));
        rows.add(buildRow("Data",_seminar.getStartDate()));
        rows.add(buildRow("Posti totali",String.valueOf(_seminar.getSeatLeft())));
        return tbody(rows);
    }
    
    
    private Element buildRow(String label,String value) {
        return tr(
            th(text(label)),
            th(text(value))
        );
    }
    
    
    private Element[] studentList() {
        List<Element> rows = new ArrayList<Element>();
        rows.add(div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
            p(attr("class -> lead"),
                h3("Studenti iscritti"))
            ))
        );
        
        for (Student student : _seminar.getStudentsList()) {
            rows.add(div(attr("class -> row"),
                div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                text(student.getName()+ " "+student.getSurname()))
                )
            );
        }
        Element[] elements= rows.toArray(new Element[rows.size()]);
        return elements;
    }
    
    
    private Element getUnderDescription() {
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                p(attr("class -> lead"),
                  h3("Descrizione corso")
                ),
                text(_seminar.getDescription())
             )
          );
    }
    
    @Override
    public List<Element> print() {
        List<Element> elements = new ArrayList<Element>();
        elements.add(buildTable());
        elements.add(getUnderDescription());
        for (Element element: studentList()) {
            elements.add(element);
        }
        return elements;
    }
}
