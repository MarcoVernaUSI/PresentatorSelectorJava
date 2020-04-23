package com;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;

import com.controller.Controller;
import com.controller.CreateSeminarController;
import com.controller.CreateStudentController;
import com.controller.NotFoundController;
import com.controller.SeminarController;
import com.controller.SeminarDeleteController;
import com.controller.SeminarListController;
import com.controller.SeminarUpdateController;
import com.controller.StudentDeleteController;
import com.controller.StudentUpdateController;
import com.controller.StudentsListController;

public class ControllerFactory {

	public List<Controller> create() {
		return new ArrayList<Controller>(asList(
		    new SeminarListController(),
		    new SeminarController(),
		    new CreateSeminarController(),
		    new SeminarDeleteController(),
		    new SeminarUpdateController(),
		    new StudentsListController(),
		    new CreateStudentController(),
		    new StudentUpdateController(),
		    new StudentDeleteController(),
		    new NotFoundController()
		    )
		);
	}
}
