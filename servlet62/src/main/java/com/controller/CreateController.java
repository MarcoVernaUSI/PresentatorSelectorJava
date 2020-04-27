package com.controller;

import java.util.regex.Pattern;

import com.Context;
import com.controller.entities.Entity;
import com.view.Layout;
import com.view.View;

public class CreateController implements Controller{
    
    Entity _entity;
    
    public CreateController(Entity entity) {
        _entity = entity;
    }

    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/"+_entity.getRoute()+"/create|/)$", route);
     }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        context.response().getWriter().write(new Layout("Create", buildPage(context)).build().render());
    }
    
    public View buildPage(Context context) {
        
        if (context.post()) {
            if(_entity.isValid(context.requestMap())) {
             _entity.getDbMapper(context).insert(context.requestMap());   
             return _entity.getListView(context);
            }}
        
        return _entity.getFormView(context);
    }
}
