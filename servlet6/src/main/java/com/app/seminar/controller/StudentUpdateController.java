package com.app.seminar.controller;

import static com.app.seminar.model.Student.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.Context;
import com.app.controller.Controller;
import com.app.seminar.model.EntityModel;
import com.app.seminar.model.Student;
import com.app.seminar.model.mapper.StudentMapper;
import com.app.seminar.view.Component;
import com.app.seminar.view.FeedBack;
import com.app.seminar.view.Form;
import com.app.seminar.view.Html;
import com.app.seminar.view.Layout;
import com.app.seminar.view.ViewUtil;

public class StudentUpdateController implements Controller {
    @Override
    public boolean handles(String route) {
      return Pattern.matches("^(/student/)[\\d]+$", route) || Pattern.matches("^(/student/update)$", route);
    }

    @Override
    public void execute(Context context) throws Exception {
        FeedBack feedBack = new FeedBack();
        if (context.post()) {    
            EntityModel entityModel = new EntityModel(Student.class, Student.rules(), context.requestMap());
            if (new ControllerUtil().saveStudent(context, entityModel, "/student")) {
                return;
            } else {
                feedBack = new ViewUtil().feedback(entityModel, context);
            }
        } else {
            String studentId = context.requestUri().replaceAll("\\D", "");
            final Student student = new StudentMapper(context.connection()).findById(studentId);
            
            Map<String, Component> map = new HashMap<String, Component>(){{
                put(ID , new Component(student.getId().toString()));
                put(FIRST_NAME , new Component(student.getName()));
                put(LAST_NAME , new Component(student.getLastName()));
            }};
            feedBack = new FeedBack(map);
        }
        List<String> components = Arrays.asList(FIRST_NAME, LAST_NAME, ID);
        Html form = new Form(feedBack,"", components, "id");
        context.response().getWriter().write(new Layout("update student", form).build().render());
    }
}
