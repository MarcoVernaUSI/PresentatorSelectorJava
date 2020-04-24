package com.controller;

import java.util.regex.Pattern;

import com.Context;
import com.model.Entity;
import com.view.Layout;
import com.view.View;

public class DeleteController<T> implements Controller{

    Entity<T> _entity;
    
    public DeleteController(Entity<T> entity) {
        _entity = entity;
    }
    
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/"+ _entity.getRouteRoot() +"/delete/)[\\d]+$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
        
        context.response().getWriter().write(new Layout("List", buildPage(context)).build().render());
    }

    public View buildPage(Context context) {
        String id = context.requestUri().replaceAll("\\D", "");
        _entity.getMapper(context).delete(id);
        
        return _entity.getListView(context);
    }
    
}
