package com.app.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.app.seminar.controller.MainController;

public class MainControllerTest {

	@Test
	public void handlesRoute() throws Exception {
		assertTrue(new MainController().handles("/"));
	}
}
