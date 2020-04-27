package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.controller.entities.SeminarEntity;
import com.controller.entities.StudentEntity;
import com.model.Seminar;
import com.model.Student;

public class DeleteControllerTest {
    @Test
    public void handlesRoute() throws Exception {
        assertTrue(new DeleteController<Seminar>(new SeminarEntity()).handles("/course/delete/1"));
        assertTrue(new DeleteController<Seminar>(new SeminarEntity()).handles("/course/delete/9"));
        assertTrue(new DeleteController<Student>(new StudentEntity()).handles("/course/student/1"));
        assertTrue(new DeleteController<Student>(new StudentEntity()).handles("/course/student/9"));
            
    }

}
