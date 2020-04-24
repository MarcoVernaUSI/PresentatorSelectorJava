package com.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.Context;
import com.DBop;
import com.FakeDbConnection;
import com.FakeHttpServletRequest;
import com.FakeHttpServletResponse;
import com.model.Seminar;
import com.model.SeminarEntity;
import com.view.SeminarListView;



public class SeminarListControllerTest {
    
    
    public final static String ROUTE_SEMINAR_LIST = "/";

	@Test
	public void handlesRoute() throws Exception {
		assertTrue(new ListController<Seminar>(new SeminarEntity()).handles(ROUTE_SEMINAR_LIST));
	}
	
	@Test
	public void execute()  throws Exception  {
	    FakeHttpServletRequest request = new FakeHttpServletRequest(ROUTE_SEMINAR_LIST);
        FakeHttpServletResponse response = new FakeHttpServletResponse();
        List<Seminar> data = new ArrayList<Seminar>() {{
            add(new Seminar(1, "Lugano", 10, "Corso di esempio", "Esempio", "01/01/2020"));
            add(new Seminar(2, "Mendrisio", 5, "Corso di esempio 2", "Esempio 2", "01/02/2020"));
        }};
        
        ListController<Seminar> controller = new ListController<Seminar>(new SeminarEntity());
        Context context = new Context(request, response, new FakeDbConnection<Seminar>(data, DBop.FIND_ALL));
        
        controller.execute(context);

        assertThat(response.message(), containsString("Corso di esempio"));
        assertThat(response.message(), containsString("Corso di esempio 2"));    
	}
	
	@Test
    public void loadList() throws Exception {
	    FakeHttpServletRequest request = new FakeHttpServletRequest(ROUTE_SEMINAR_LIST);
        FakeHttpServletResponse response = new FakeHttpServletResponse();	    
	    
	    List<Seminar> data = new ArrayList<Seminar>() {{
	        add(new Seminar(1, "Lugano", 10, "Corso di esempio", "Esempio", "01/01/2020"));
	        add(new Seminar(2, "Mendrisio", 5, "Corso di esempio 2", "Esempio 2", "01/02/2020"));
	    }};
	    
	    ListController<Seminar> controller = new ListController<Seminar>(new SeminarEntity());
        Context context = new Context(request, response, new FakeDbConnection<Seminar>(data, DBop.FIND_ALL));
	       
	    SeminarListView result = (SeminarListView) controller.buildPage(context);
	    List<Seminar> seminars = new ArrayList<Seminar>();
        for (Seminar seminar : result.getContent()) {
            seminars.add(seminar);
        }
	    
        assertEquals(2,seminars.size());
	    assertEquals("Corso di esempio",seminars.get(0).getName());
	    assertEquals("Corso di esempio 2",seminars.get(1).getName());
    }
}
