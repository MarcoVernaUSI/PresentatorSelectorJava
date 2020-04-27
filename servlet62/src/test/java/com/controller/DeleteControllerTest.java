package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.controller.entities.SeminarEntity;
import com.controller.entities.StudentEntity;

public class DeleteControllerTest {
    @Test
    public void handlesRoute() throws Exception {
        assertTrue(new DeleteController(new SeminarEntity()).handles("/course/delete/1"));
        assertTrue(new DeleteController(new SeminarEntity()).handles("/course/delete/9"));
        assertTrue(new DeleteController(new StudentEntity()).handles("/student/delete/1"));
        assertTrue(new DeleteController(new StudentEntity()).handles("/student/delete/9"));
            
    }

}
