package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.model.Seminar;
import com.model.SeminarEntity;
import com.model.Student;
import com.model.StudentEntity;

public class DeleteControllerTest {
    @Test
    public void handlesRoute() throws Exception {
        assertTrue(new DeleteController<Seminar>(new SeminarEntity()).handles("/course/delete/1"));
        assertTrue(new DeleteController<Seminar>(new SeminarEntity()).handles("/course/delete/9"));
        assertTrue(new DeleteController<Student>(new StudentEntity()).handles("/course/student/1"));
        assertTrue(new DeleteController<Student>(new StudentEntity()).handles("/course/student/9"));
            
    }

}
