package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.controller.entities.SeminarEntity;
import com.controller.entities.StudentEntity;

public class CreateControllerTest {

    @Test
    public void handlesRoute() {
        assertTrue(new CreateController(new SeminarEntity()).handles("/course/create"));
        assertTrue(new CreateController(new StudentEntity()).handles("/student/create"));
}  
}
