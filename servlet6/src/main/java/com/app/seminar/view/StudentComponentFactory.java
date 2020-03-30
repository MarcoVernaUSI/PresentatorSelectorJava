package com.app.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.app.seminar.model.Entity;
import com.app.seminar.model.Student;
import com.github.manliogit.javatags.element.Element;

public class StudentComponentFactory implements RowFactory {
    @Override
    public Element row(Entity e) {
        Student s = (Student) e; 
        return 
            tr(
                th(attr("scope -> row"), a(attr("href -> /student/" + s.getId()),text(s.getName()))),
                td(text(s.getLastName())),
                td(a(attr("href -> /student/delete/" + s.getId()),"delete"))
            );
    }

}
