package com.controller;

import java.util.Map;
import java.util.regex.Pattern;

import com.Context;
import com.controller.entities.Entity;
import com.view.Layout;
import com.view.View;

public class UpdateController implements Controller{
    
    Entity _entity;
    
    public UpdateController(Entity entity) {
        _entity = entity;
    }

    
    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/"+_entity.getRoute()+"/)[\\d]+$", route);
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
        Map<String,String> defaultFields = _entity.getValues(context, id);
        
        
        if (context.post()) {
            defaultFields = context.requestMap();
            if (_entity.isValid(context.requestMap())) {
                _entity.getDbMapper(context).update(context.requestMap(), id);                
                return _entity.getListView(context);
            }
        } 
        
        return _entity.getFormView(context, defaultFields);
    }
}
