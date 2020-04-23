package com.view;
import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import com.github.manliogit.javatags.element.Element;
import com.model.Seminar;

public class SeminarListView extends ListView<Seminar>{
    
    
    public SeminarListView(Iterable<Seminar> seminars, Iterable<String> header) {
        super(seminars, header);
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
}
