package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class SeminarControllerTest {
    @Test
    public void handlesRoute() throws Exception {
        assertTrue(new SeminarController().handles("/course/html/1"));
        assertTrue(new SeminarController().handles("/course/html/9"));
        assertTrue(new SeminarController().handles("/course/csv/1"));
        assertTrue(new SeminarController().handles("/course/csv/9"));
        assertFalse(new SeminarController().handles("/course/1"));
        assertFalse(new SeminarController().handles("/course/9"));
    }

}
