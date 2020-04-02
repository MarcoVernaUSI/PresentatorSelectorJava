package com.app.controller;

import static org.junit.Assert.*;

import org.junit.Test;

import com.controller.SeminarListController;

public class MainControllerTest {

	@Test
	public void handlesRoute() throws Exception {
		assertTrue(new SeminarListController().handles("/"));
	}
}
