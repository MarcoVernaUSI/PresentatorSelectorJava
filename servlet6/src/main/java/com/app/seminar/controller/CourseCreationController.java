package com.app.seminar.controller;

import static com.app.seminar.model.Course.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.Context;
import com.app.controller.Controller;
import com.app.seminar.model.Course;
import com.app.seminar.model.EntityModel;
import com.app.seminar.view.FeedBack;
import com.app.seminar.view.Form;
import com.app.seminar.view.Html;
import com.app.seminar.view.Layout;
import com.app.seminar.view.ViewUtil;

public class CourseCreationController implements Controller {

    private final String _path = "/course/create";

    @Override
    public boolean handles(String route) {
        return _path.equals(route);
    }

    @Override
    public void execute(Context context) throws Exception {
        context.response().setContentType("text/html");
        context.response().setCharacterEncoding("UTF-8");

        FeedBack feedBack = new FeedBack();
        if (context.post()) {
            EntityModel entity = new EntityModel(Course.class, Course.rules(), context.requestMap());
            if (new ControllerUtil().saveCourse(context, entity, "/course")) {
                return;
            } else {
                feedBack = new ViewUtil().feedback(entity, context);
            }
        }
        viewForm(context, feedBack);
    }

    private void viewForm(Context context, FeedBack feedBack) throws IOException {

        List<String> components = Arrays.asList(NAME, START, LOCATION, TOTAL_SEATS, ID);
        Html form = new Form(feedBack, "create", components, Course.ID);
        context.response().getWriter().write(new Layout("create course", form).build().render());
    }
}
