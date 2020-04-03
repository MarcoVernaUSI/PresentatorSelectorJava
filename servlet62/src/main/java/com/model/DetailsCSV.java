package com.model;

import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;

public class DetailsCSV implements Details{
    
    private final Seminar _seminar;

    public DetailsCSV(Seminar seminar) {
        _seminar = seminar;
    }

    public void writeCsvToFile() {
        FileWriter csvWriter;
        try {
            csvWriter = new FileWriter("src/main/savedCsv/"+_seminar.getName().replaceAll(" ", "")+".csv");
            csvWriter.append(header());
            for (Student student : _seminar.getStudentsList()) {
                csvWriter.append(newRow(student));
                }
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
     
    private void  writeRows(List<Element> rows) {
        rows.add(text(header()));
        for (Student student : _seminar.getStudentsList()) {
            rows.add(text(newRow(student)));
        }
    }
    
    private String newRow(Student student) {
        return "\""+_seminar.getId()+"\";\""+_seminar.getName()+"\";\""+_seminar.getLocation()+"\";\""+_seminar.getStartDate()+"\";\""+_seminar.getTotalSeats()+"\";\""+_seminar.getDescription()+"\";\""+student.getId()+"\";\""+student.getName()+"\";\""+student.getSurname()+"\"\n";
    }
    
    private String header() {
        return "\"SeminarId\";\"CourseName\";\"Location\";\"StartDate\";\"TotalSeat\";\"CourseDescription\";\"StudentId\";\"StudentName\";\"StudentSurname\"\n";
    }
    
    @Override
    public List<Element> print() {
        List<Element> elements = new ArrayList<Element>();
        elements.add(div(attr("class -> row"),
                div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                    p(attr("class -> lead"),
                      h3("Prewiev")
                    )                 )
              ));
        writeRows(elements);
        
        elements.add(
            form(attr("id -> getCsvId", "name -> getCsv", "method -> post"),
                div(attr( "id -> getCsvBtn"),
                    input(attr("id -> btn", "name -> submit", "type -> submit",  "value -> Download CSV", "class -> btn btn-primary", "onclick -> callServlet();"))                                     )   
                )
            );
        return elements;
    }

}
