package com.app.seminar.view;
import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.app.seminar.model.Seminar;
import com.github.manliogit.javatags.element.Element;

public class SeminarList implements View {
    
    private final Iterable<Seminar> _seminars;
    private final Iterable<String> _header;
    
    public SeminarList(Iterable<Seminar> seminars, Iterable<String> header) {
        _seminars = seminars;
        _header = header;
    }

    private Element header() {
        List<Element> list = new ArrayList<Element>();
        for (String component : _header) {
            list.add(th(text(component)));
        }
        return thead(tr(group(list)));
    }

    private Element body() {
        List<Element> rows = new ArrayList<Element>();
        for (Seminar seminar : _seminars) {
            rows.add(buildRow(seminar));
        }
        return tbody(rows);
    }
    
    private Element buildRow(Seminar seminar) {
        return tr(
            //th(attr("scope -> row"), a(attr("href -> /course/" + seminar.getCourse().getNumber()),text(seminar.getName()))),
            th(text(seminar.getName())),
            td(text(seminar.getLocation())),
            td(text((String.valueOf(seminar.getSeatLeft())))),
            td(text(seminar.getStartDate())),
            td(a(attr("href -> /course/csv/" + seminar.getCourse().getNumber()),"get")),
            td(a(attr("href -> /course/html/" + seminar.getCourse().getNumber()),"get"))
        );
    }

    @Override
    public Element build() {
        return table(attr("class -> table table-striped"), header(), body());
    }

}
