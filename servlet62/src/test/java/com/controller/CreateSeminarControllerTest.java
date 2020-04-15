package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreateSeminarControllerTest {

    @Test
    public void handlesRoute() throws Exception {
        assertTrue(new CreateSeminarController().handles("/course/create"));
    }  
}
