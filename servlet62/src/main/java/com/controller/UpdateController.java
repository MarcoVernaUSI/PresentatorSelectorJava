package com.controller;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.Context;
import com.model.Entity;
import com.view.FormView;
import com.view.Layout;
import com.view.View;

public class UpdateController<T> implements Controller{
    
    Entity<T> _entity;
    
    public UpdateController(Entity<T> entity) {
        _entity = entity;
    }

    
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/"+_entity.getRouteRoot()+"/)[\\d]+$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        
        context.response().getWriter().write(
            new Layout("Update", buildPage(context)).build().render());
    }

    public View buildPage(Context context) {
        String id = context.requestUri().replaceAll("\\D", "");
        Map<String,String> defaultFields = _entity.getFieldsValues(_entity.getMapper(context).findById(id));
        
        
        Map<String,String> fields = _entity.getFieldsTypes();
        Map<String,List<String>> errors = _entity.validate(context.requestMap());
    
        if (context.post()) {
            defaultFields = context.requestMap();
            
            if (_entity.isValid(context.requestMap())) {
                _entity.getMapper(context).update(
                    _entity.build(context.requestMap()), id);
                
                    return _entity.getListView(context);
            }
        }
         
        return new FormView("/"+_entity.getRouteRoot()+"/"+id, fields, errors, context, defaultFields);
    }
}
