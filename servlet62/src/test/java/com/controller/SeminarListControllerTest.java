package com.controller;

import static org.junit.Assert.*;

import org.junit.Test;



public class SeminarListControllerTest {

	@Test
	public void handlesRoute() throws Exception {
		assertTrue(new SeminarListController().handles("/"));
	}
	
//	@Test
 //   public void executeTest() throws Exception {
  //      FakeHttpServletRequest request = new FakeHttpServletRequest("/");
 //       FakeHttpServletResponse response = new FakeHttpServletResponse();
        //Iterable<Seminar> seminars = new SeminarMapper(context.connection()).findAll();

        

//        assertThat(response.statusCode(), is(HttpServletResponse.SC_OK));
  //      assertThat(response.message(), containsString("form"));
 //   }

	
}
