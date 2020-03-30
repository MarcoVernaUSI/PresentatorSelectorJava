package com.app.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.app.seminar.model.Course;
import com.app.seminar.model.Entity;
import com.github.manliogit.javatags.element.Element;

public class CourseComponentFactory implements RowFactory {
    @Override
    public Element row(Entity e) {
        Course c = (Course)e; 
        return tr(
                th(attr("scope -> row"), a(attr("href -> /course/" + c.getId()),text(c.getName()))),
                td(text(c.getLocation())),
                td(text(c.getTotalSeats().toString())),
                td(text(c.getStartDate().toString())),
                td(a(attr("href -> /course/delete/" + c.getId()),"delete"))
            );
    }

}
