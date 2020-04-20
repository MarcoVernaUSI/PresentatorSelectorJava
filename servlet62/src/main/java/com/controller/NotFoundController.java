package com.controller;

import com.Context;
import com.view.Layout;
import com.view.NoPageFound;

public class NotFoundController implements Controller{
    @Override
    public boolean handles(String route) {
        return true;
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
      
        context.response().getWriter().write(new Layout("404", new NoPageFound()).build().render());
    }
}
