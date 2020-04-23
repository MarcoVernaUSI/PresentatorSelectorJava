package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeleteControllerTest {
    @Test
    public void handlesRoute() throws Exception {
        assertTrue(new SeminarDeleteController().handles("/course/delete/1"));
        assertTrue(new SeminarDeleteController().handles("/course/delete/9"));
        }

}
