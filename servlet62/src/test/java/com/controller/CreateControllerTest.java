package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.controller.entities.SeminarEntity;
import com.controller.entities.StudentEntity;
import com.model.Seminar;
import com.model.Student;

public class CreateControllerTest {

    @Test
    public void handlesRoute() {
        assertTrue(new CreateController<Seminar>(new SeminarEntity()).handles("/course/create"));
        assertTrue(new CreateController<Student>(new StudentEntity()).handles("/student/create"));
}  
}
