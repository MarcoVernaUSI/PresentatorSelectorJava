package com.app.seminar.view;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.app.seminar.model.Seminar;
import com.app.seminar.model.Student;
import com.github.manliogit.javatags.element.Element;

public class CSVSeminar implements View  {
    private final Seminar _seminar;
    
    public CSVSeminar(Seminar seminar) {
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
                    bodyList()
                    )
            )
        );
    }

    public  List<Element> studentList() {
        List<Element> rows = new ArrayList<Element>();
        for (Student student : _seminar.getStudentsList()) {
            rows.add(text("\""+student.getName()+ "\";\""+student.getSurname()+"\"\n"));
        }
        
        return rows;
    }
    
    public Element[] bodyList() {
        List<Element> studentList = studentList();
        Element[] bodyList= new Element[studentList.size()+1];
        bodyList[0]=text("\""+_seminar.getNumber()+"\";\""+_seminar.getName()+"\";\""+_seminar.getStartDate()+"\";\""+_seminar.getLocation()+"\";\""+_seminar.getSeatLeft()+"\"\n");
         for (int i=1; i<studentList.size()+1;i++) {
             bodyList[i]= studentList.get(i);
         }
        
        return bodyList;
    }
}
