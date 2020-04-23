package com.view;
import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.github.manliogit.javatags.element.Element;

public abstract class ListView<T> implements View {
    
    private final Iterable<T> _entries;
    private final Iterable<String> _header;
    
    public ListView(Iterable<T> seminars, Iterable<String> header) {
        _entries = seminars;
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
        for (T entry : _entries) {
            rows.add(buildRow(entry));
        }
        return tbody(rows);
    }
    

    public abstract Element buildRow(T entry);

    @Override
    public Element[] getBody() {
        Element[] elements ={buildTable()};
        return elements;
    }
    
    public Iterable<T> getContent() {
        return _entries;
    }

}
