package com.app.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.app.seminar.model.Seminar;
import com.app.seminar.model.Student;
import com.github.manliogit.javatags.element.Element;

public class HTMLSeminar implements View  {
    private final Seminar _seminar;
    
    public HTMLSeminar(Seminar seminar) {
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
    public Element getBody() {
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
                    text(_seminar.getCourse().getDescription())
                  )
            ),
            div(p(attr("class -> lead"),
                studentList()
              )
                
            )
          );
    }
    
    private Element studentList() {
        List<Element> rows = new ArrayList<Element>();
        for (Student student : _seminar.getStudentsList()) {
            rows.add(text(student.getName()+" "+student.getSurname()));
        }
        return tbody(rows);
    }
}
