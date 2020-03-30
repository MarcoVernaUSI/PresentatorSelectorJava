package com.app.seminar.controller;

import com.Context;
import com.app.controller.Controller;

public class MainController implements Controller {

	@Override
	public boolean handles(String route) {
		return "/".equals(route);
	}

	@Override
	public void execute(Context context) throws Exception {
		context.response().setContentType("text/html");
		context.response().setCharacterEncoding("UTF-8");

		//context.response().getWriter().write(new Layout("Main", "blablala").build().render());
	}
}
