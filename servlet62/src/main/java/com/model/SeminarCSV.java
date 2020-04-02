package com.model;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;

public class SeminarCSV extends Seminar{
    


    public SeminarCSV(int id, String location, int totalSeats, Course course, String startDate) {
        super(id, location, totalSeats, course, startDate);
    }


    protected Element getHeader() {
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                h1(getCourse().getCourseName())
              )
         );
    }
    
    
    private Element[] studentList() {
        List<Element> rows = new ArrayList<Element>();
        for (Student student : getStudentsList()) {
            rows.add(text("\""+student.getName()+ "\";\""+student.getSurname()+"\"\n"));
        }
        Element[] elements= rows.toArray(new Element[rows.size()]);
        return elements;
    }

    protected Element getBody() {
        return text("\""+getId()+"\";\""+getName()+"\";\""+getStartDate()+"\";\""+getLocation()+"\";\""+getSeatLeft()+"\"\n");
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
