package com;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;

import com.controller.Controller;
import com.controller.CreateController;
import com.controller.DeleteController;
import com.controller.ListController;
import com.controller.NotFoundController;
import com.controller.SeminarController;
import com.controller.UpdateController;
import com.model.Seminar;
import com.model.SeminarEntity;
import com.model.Student;
import com.model.StudentEntity;

public class ControllerFactory {

	public List<Controller> create() {
		return new ArrayList<Controller>(asList(
		    new SeminarController(),
		    
		    new ListController<Seminar>(new SeminarEntity()),
		    new ListController<Student>(new StudentEntity()),
            
		    
		    new UpdateController<Seminar>(new SeminarEntity()),
		    new UpdateController<Student>(new StudentEntity()),
		    
		    new CreateController<Seminar>(new SeminarEntity()),
		    new CreateController<Student>(new StudentEntity()),
		    
		    new DeleteController<Seminar>(new SeminarEntity()),
            new DeleteController<Student>(new StudentEntity()),
		    
		    new NotFoundController()
		    )
		);
	}
}
