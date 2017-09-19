package com.libertymutual.spark.myapp.controllers;

import java.util.HashMap;
import java.util.Map;

import com.libertymutual.spark.myapp.models.User;
import com.libertymutual.spark.myapp.utilities.MustacheRenderer;

import spark.Request;
import spark.Response;
import spark.Route;

public class SessionController {

	public static final Route newForm = (Request req, Response res) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		return MustacheRenderer.getInstance().render("/session/login.html", model);
	};
	
	public static final Route create = (Request req, Response res) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		User user = new User(req.queryParams("username"), req.queryParams("password"), 
							 req.queryParams("firstName"), req.queryParams("lastName"));
		model.put("user", user);
		return MustacheRenderer.getInstance().render("/session/login.html", model);
	};

}
