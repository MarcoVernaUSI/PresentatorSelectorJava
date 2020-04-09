package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class CreateSeminarTest {

    @Test
    public void handlesRoute() throws Exception {
        assertTrue(new CreateSeminarController().handles("/create"));
    }  
}
