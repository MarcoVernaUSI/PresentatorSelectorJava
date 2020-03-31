package com.app.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.app.seminar.controller.CourseListController;

public class MainControllerTest {

	@Test
	public void handlesRoute() throws Exception {
		assertTrue(new CourseListController().handles("/"));
	}
}
