package com.app.controller;

import com.Context;
import com.app.seminar.view.NoPageFound;

public class NotFoundController implements Controller {

    @Override
    public boolean handles(String route) {
        return true;
//        return !Pattern.matches(".*/course$", route) &&
//               !Pattern.matches(".*/course/create$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");
        context.response().getWriter().write(new NoPageFound().renderHtmlLayout());
    }

}
