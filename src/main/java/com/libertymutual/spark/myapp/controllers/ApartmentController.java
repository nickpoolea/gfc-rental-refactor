package com.libertymutual.spark.myapp.controllers;

import spark.Response;

import java.util.HashMap;
import java.util.Map;

import com.libertymutual.spark.myapp.models.Apartment;
import com.libertymutual.spark.myapp.utilities.FreeMarkerRenderer;

import spark.Request;
import spark.Route;

public class ApartmentController {

	public static final Route details = (Request req, Response res) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		Apartment apartment = Apartment.findById(Integer.parseInt(req.params("id")));
		model.put("apartment", apartment);
		return FreeMarkerRenderer.getInstance().render("/apartments/list.html", model);
	};
	
}
