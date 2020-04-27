package com.view;
import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.Iterator;

import com.github.manliogit.javatags.element.Element;
import com.model.Student;

public class StudentListView extends ListView<Student>{
    
    
    public StudentListView(Iterable<Student> students) {
        super(students);
    }

    @Override
    protected Element buildRow(Student student) {
        return tr(
            th(a(attr("href -> /student/" + student.getId()),student.getName())),
            td(text(student.getSurname())),
            td(a(attr("href -> /student/delete/" + student.getId()),"delete"))
        );
    }
    
  @Override
  protected Iterable<String> getTableHeader(){
        
        Iterable<String> fields =Student.getFieldList();
        
        ArrayList<String> header = new ArrayList<String>();

        Iterator<String> iterator = fields.iterator();
        while(iterator.hasNext()){
            header.add(iterator.next());
        }
        
        header.add("Delete");     
        return header;
    }
}
