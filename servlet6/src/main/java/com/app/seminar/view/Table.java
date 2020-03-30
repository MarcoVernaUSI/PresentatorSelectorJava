package com.app.seminar.view;
import static com.github.manliogit.javatags.lang.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;

import com.app.seminar.model.Entity;
import com.github.manliogit.javatags.element.Element;

public class Table implements Html {
    
    private final Iterable<? extends Entity> _entities;
    private final Iterable<String> _header;
    private final RowFactory _factory;
    
    public Table(Iterable<? extends Entity> entities, Iterable<String> header, RowFactory factory) {
        _entities = entities;
        _header = header;
        _factory = factory;
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
        for (Entity entity : _entities) {
            rows.add(_factory.row(entity));
        }
        return tbody(rows);
    }

    @Override
    public Element build() {
        return table(attr("class -> table table-striped"), header(), body());
    }

}
