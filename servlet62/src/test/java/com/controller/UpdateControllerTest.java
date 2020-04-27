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
import com.controller.entities.SeminarEntity;
import com.model.Seminar;

public class UpdateControllerTest {

    public final static String ROUTE_SEMINAR_LIST = "/course/1";
    
    @Test
    public void handlesRoute() throws Exception {
        assertTrue(new UpdateController(new SeminarEntity()).handles("/course/1"));
        assertTrue(new UpdateController(new SeminarEntity()).handles("/course/2"));
        assertTrue(new UpdateController(new SeminarEntity()).handles("/course/9"));
    }

    

    @Test
    public void execute()  throws Exception  {
        FakeHttpServletRequest request = new FakeHttpServletRequest(ROUTE_SEMINAR_LIST);
        FakeHttpServletResponse response = new FakeHttpServletResponse();
        List<Seminar> data = new ArrayList<Seminar>() {{
            add(new Seminar(1, "Lugano", 10, "Corso di esempio", "Esempio", "01/01/2020"));
        }};
        
        UpdateController controller = new UpdateController(new SeminarEntity());
        Context context = new Context(request, response, new FakeDbConnection<Seminar>(data, DBop.FIND_BY_ID, 1));
        
        controller.execute(context);

        assertThat(response.message(), containsString("gahdfhdh"));

    }
    
}
