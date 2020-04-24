package com.controller;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.Context;
import com.model.Entity;
import com.view.FormView;
import com.view.Layout;
import com.view.View;

public class CreateController<T> implements Controller{
    
    Entity<T> _entity;
    
    public CreateController(Entity<T> entity) {
        _entity = entity;
    }

    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/"+_entity.getRouteRoot()+"/create|/)$", route);
     }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        context.response().getWriter().write(new Layout("Create", buildPage(context)).build().render());
    }
    
    public View buildPage(Context context) {
        Map<String,String> fields = _entity.getFieldsTypes();
        Map<String,List<String>> errors = _entity.validate(context.requestMap());
    
        if (context.post() && _entity.isValid(context.requestMap())) {
             _entity.getMapper(context).insert(
                 _entity.build(context.requestMap()));   
             return _entity.getListView(context);        
            } 
        return new FormView(context.requestUri(), fields, errors, context, context.requestMap());
    }
}
