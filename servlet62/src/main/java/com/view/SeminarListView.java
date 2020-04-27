package com.view;
import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.Iterator;

import com.github.manliogit.javatags.element.Element;
import com.model.Seminar;

public class SeminarListView extends ListView<Seminar>{
    
    
    public SeminarListView(Iterable<Seminar> seminars) {
        super(seminars);
    }

    @Override
    public Element buildRow(Seminar seminar) {
        return tr(
            th(a(attr("href -> /course/" + seminar.getId()),seminar.getName())),
            td(text(seminar.getLocation())),
            td(text((String.valueOf(seminar.getSeatLeft())))),
            td(text(seminar.getFormattedDate())),
            td(a(attr("href -> /course/csv/" + seminar.getId()),"get")),
            td(a(attr("href -> /course/html/" + seminar.getId()),"get")),
            td(a(attr("href -> /course/delete/" + seminar.getId()),"delete"))
        );
    }
    

    @Override
    protected Iterable<String> getTableHeader(){
        Iterable<String> fields =Seminar.getFieldList();
        
        ArrayList<String> header = new ArrayList<String>();

        //iterate through current objects and add them to new list
        Iterator<String> iterator = fields.iterator();
        while(iterator.hasNext()){
            header.add(iterator.next());
        }
        
        header.add("csv");
        header.add("html");
        header.add("Delete");
        return header;
    }
}
