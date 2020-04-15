package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class DeleteControllerTest {
    @Test
    public void handlesRoute() throws Exception {
        assertTrue(new DeleteController().handles("/course/delete/1"));
        assertTrue(new DeleteController().handles("/course/delete/9"));
        }

}
