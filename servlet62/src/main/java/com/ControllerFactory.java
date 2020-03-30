package com;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;

import com.app.controller.Controller;
import com.app.seminar.controller.CourseListController;
import com.app.seminar.controller.HtmlCourseController;

public class ControllerFactory {

	public List<Controller> create() {
		return new ArrayList<Controller>(asList(
		   // new MainController(),
		    new CourseListController(),
		    new HtmlCourseController()
		    )
		    );
	
	}
}
