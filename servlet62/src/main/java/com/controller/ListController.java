package com.controller;

import java.util.regex.Pattern;

import com.Context;
import com.model.Entity;
import com.view.Layout;
import com.view.View;

public class ListController<T> implements Controller{
    
    
    Entity<T> _entity;
     
    public ListController(Entity<T> entity) {
        _entity = entity;
    }

    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/"+_entity.getRouteRoot()+"|/)$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
      
        context.response().getWriter().write(new Layout("List",buildPage(context)).build().render());
    }
    
    public View buildPage(Context context) {
        Iterable<T> seminars = _entity.getMapper(context).findAll();
        Iterable<String> header = _entity.getFieldList();   
        
        return  _entity.getListView(seminars, header);
    }
}
