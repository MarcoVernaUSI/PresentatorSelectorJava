package com.app.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.app.seminar.controller.CourseCreationController;

public class MainControllerTest {

	@Test
	public void handlesRoute() throws Exception {
		assertTrue(new CourseCreationController().handles("/"));
	}
}
