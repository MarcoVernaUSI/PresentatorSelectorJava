package com.app.seminar.controller;

import java.io.IOException;

import com.Context;
import com.app.seminar.model.Course;
import com.app.seminar.model.EntityModel;
import com.app.seminar.model.Student;
import com.app.seminar.model.mapper.CourseMapper;
import com.app.seminar.model.mapper.StudentMapper;

public class ControllerUtil {

    public boolean saveStudent(Context context, EntityModel entityModel, String redirect) throws IOException {
        if (entityModel.isValid()) {
            Student course = entityModel.create();
            new StudentMapper(context.connection()).save(course);

            context.response().sendRedirect(redirect);
            return true;
        }
        return false;
    }

    public boolean saveCourse(Context context, EntityModel entityModel, String route) throws IOException {
        if (entityModel.isValid()) {
            Course course = entityModel.create();
            new CourseMapper(context.connection()).save(course);

            context.response().sendRedirect(route);
            return true;
        }
        return false;
    }
}
