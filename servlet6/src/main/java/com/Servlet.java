package com;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.app.controller.Controller;

public class Servlet extends HttpServlet {

	
//	private SQLiteConnectionPoolDataSource _ds;
//	_ds = new SQLiteConnectionPoolDataSource();
//	_ds.setUrl("jdbc:sqlite:/path");
	
//	private HikariDataSource _ds;
//  _ds = new HikariDataSource();
//	_ds.setJdbcUrl("jdbc:sqlite:/path");
	
	private DataSource _ds;
	
	@Override
	public void init() throws ServletException {
		try {
			_ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/ds");
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    
		for(Controller controller : new CourseFactory().create()){
		    if(controller.handles(req.getRequestURI())){
                ConnectionHandler connection = new ConnectionHandler(_ds);
                try {
                    connection.setAutoCommit(false);
                    resp.setStatus(HttpServletResponse.SC_OK);
                    controller.execute(new Context(req, resp, connection.get()));
                    connection.commit();
                    
                    return ;
                } catch (Exception e) {
                    resp.setContentType("text/html;charset=UTF-8");
                    resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    connection.rollback();
                    e.getCause().printStackTrace();
                    throw new RuntimeException(e);
                } finally {
                    resp.getWriter().flush();
                    connection.close();
                }
            }
		}
		resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
}
