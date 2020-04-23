package com.view;
import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.github.manliogit.javatags.element.Element;
import com.model.Student;

public class StudentListView extends ListView<Student>{
    
    
    public StudentListView(Iterable<Student> students, Iterable<String> header) {
        super(students, header);
    }

    @Override
    public Element buildRow(Student student) {
        return tr(
            th(a(attr("href -> /student/" + student.getId()),student.getName())),
            td(text(student.getSurname())),
            td(a(attr("href -> /student/delete/" + student.getId()),"delete"))
        );
    }
}
