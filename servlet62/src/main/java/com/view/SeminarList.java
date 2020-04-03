package com.view;
import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;
import com.model.Seminar;

public class SeminarList implements View {
    
    private final Iterable<Seminar> _seminars;
    private final Iterable<String> _header;
    
    public SeminarList(Iterable<Seminar> seminars, Iterable<String> header) {
        _seminars = seminars;
        _header = header;
    }

    private Element buildTable() {        
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-8 col-sm-9"),
                table(attr("class -> table table-striped"), tableHeader(), TableBody())
            )
          );
    }
    
    
    private Element tableHeader() {
        List<Element> list = new ArrayList<Element>();
        for (String component : _header) {
            list.add(th(text(component)));
        }
        return thead(tr(group(list)));
    }

    private Element TableBody() {
        List<Element> rows = new ArrayList<Element>();
        for (Seminar seminar : _seminars) {
            rows.add(buildRow(seminar));
        }
        return tbody(rows);
    }
    
    private Element buildRow(Seminar seminar) {
        return tr(
            th(text(seminar.getName())),
            td(text(seminar.getLocation())),
            td(text((String.valueOf(seminar.getSeatLeft())))),
            td(text(seminar.getStartDate())),
            td(a(attr("href -> /course/csv/" + seminar.getId()),"get")),
            td(a(attr("href -> /course/html/" + seminar.getId()),"get"))
        );
    }

    @Override
    public Element getHeader() {
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-7 col-sm-6"),
                h1("Seminar" ),
                p(attr("class -> lead"),
                  text("Select seminar")
                )
             )
         );
    }


    @Override
    public Element[] getBody() {
        Element[] elements ={getHeader(),buildTable()};
        return elements;
    }

}
