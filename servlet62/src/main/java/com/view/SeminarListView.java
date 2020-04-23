package com.view;
import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;
import com.model.Seminar;

public class SeminarListView implements View {
    
    private final Iterable<Seminar> _seminars;
    private final Iterable<String> _header;
    
    public SeminarListView(Iterable<Seminar> seminars, Iterable<String> header) {
        _seminars = seminars;
        _header = header;
    }

    private Element buildTable() {        
        return div(attr("class -> row"),
            div(attr("class -> col-lg-8 col-md-8 col-sm-9"),
                table(attr("class -> table table-striped", "style -> width: 100%"), tableHeader(), TableBody())
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
    public Element[] getBody() {
        Element[] elements ={buildTable()};
        return elements;
    }
    
    public Iterable<Seminar> getContent() {
        return _seminars;
    }

}
