package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class SeminarListControllerTest {

	@Test
	public void handlesRoute() throws Exception {
		assertTrue(new SeminarListController().handles("/"));
	}	
	
}
