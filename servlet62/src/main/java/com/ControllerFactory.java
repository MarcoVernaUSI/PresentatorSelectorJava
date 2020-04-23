package com;

import static java.util.Arrays.*;

import java.util.ArrayList;
import java.util.List;

import com.controller.Controller;
import com.controller.CreateSeminarController;
import com.controller.DeleteController;
import com.controller.NotFoundController;
import com.controller.SeminarController;
import com.controller.SeminarListController;
import com.controller.UpdateController;

public class ControllerFactory {

	public List<Controller> create() {
		return new ArrayList<Controller>(asList(
		    new SeminarListController(),
		    new SeminarController(),
		    new CreateSeminarController(),
		    new DeleteController(),
		    new UpdateController(),
		    new NotFoundController()
		    )
		);
	}
}
