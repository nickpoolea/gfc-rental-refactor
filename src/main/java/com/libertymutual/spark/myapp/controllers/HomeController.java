package com.libertymutual.spark.myapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.libertymutual.spark.myapp.models.Apartment;
import com.libertymutual.spark.myapp.utilities.FreeMarkerRenderer;
import com.libertymutual.spark.myapp.utilities.MustacheRenderer;

import spark.Request;
import spark.Response;

import spark.Route;

public class HomeController {
	
	public static final Route index = (Request req, Response res) -> {
		List<Apartment> apartments = Apartment.findAll();
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("apartments", apartments);
		return FreeMarkerRenderer.getInstance().render("/home/index.html", model);
	};
	
}
