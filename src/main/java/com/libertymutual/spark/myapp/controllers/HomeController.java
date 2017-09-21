package com.libertymutual.spark.myapp.controllers;

import java.util.HashMap;
import java.util.Map;

import com.libertymutual.spark.myapp.models.Apartment;
import com.libertymutual.spark.myapp.utilities.AutoCloseableDb;
import com.libertymutual.spark.myapp.utilities.FreeMarkerRenderer;

import spark.Request;
import spark.Response;

import spark.Route;

public class HomeController {
	
	public static final Route index = (Request req, Response res) -> {
		try(AutoCloseableDb db = new AutoCloseableDb()) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("apartments", Apartment.findAll());
			model.put("currentUser", req.session().attribute("currentUser"));
			return FreeMarkerRenderer.getInstance().render("/home/index.html", model, req);
		}
	};
	
}
