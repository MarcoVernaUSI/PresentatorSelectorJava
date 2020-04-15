package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoPageFoundTest {
    @Test
    public void handlesRoute() throws Exception {
        assertTrue(new NotFoundController().handles("/xxx"));
        assertTrue(new NotFoundController().handles("/course/xxx"));
        }

}
