package com.controller;

import java.util.regex.Pattern;

import com.Context;
import com.controller.entities.Entity;
import com.view.Layout;
import com.view.View;

public class ListController implements Controller{
    
    
    Entity _entity;
     
    public ListController(Entity entity) {
        _entity = entity;
    }

    @Override
    public boolean handles(String route) {
        return Pattern.matches("^(/"+_entity.getRoute()+"|/)$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
      
        context.response().getWriter().write(new Layout("List",buildPage(context)).build().render());
    }
    
    public View buildPage(Context context) {
        return  _entity.getListView(context);
    }
}
