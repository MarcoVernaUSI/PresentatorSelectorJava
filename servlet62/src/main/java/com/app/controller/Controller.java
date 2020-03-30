package com.app.controller;

import com.Context;

public interface Controller {

	boolean handles(String route);
	void execute(Context context) throws Exception;
	
}
