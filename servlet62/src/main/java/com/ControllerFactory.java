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
import com.controller.entities.SeminarEntity;
import com.controller.entities.StudentEntity;

public class ControllerFactory {

	public List<Controller> create() {
		return new ArrayList<Controller>(asList(
		    new SeminarController(),
		    
		    new ListController(new SeminarEntity()),
		    new ListController(new StudentEntity()),
            
		    
		    new UpdateController(new SeminarEntity()),
		    new UpdateController(new StudentEntity()),
		    
		    new CreateController(new SeminarEntity()),
		    new CreateController(new StudentEntity()),
		    
		    new DeleteController(new SeminarEntity()),
            new DeleteController(new StudentEntity()),
		    
		    new NotFoundController()
		    )
		);
	}
}
