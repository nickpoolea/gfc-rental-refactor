package com.libertymutual.spark.myapp.controllers;

import spark.Response;

import java.util.HashMap;
import java.util.Map;

import com.libertymutual.spark.myapp.models.Apartment;
import com.libertymutual.spark.myapp.utilities.AutoCloseableDb;
import com.libertymutual.spark.myapp.utilities.FreeMarkerRenderer;

import spark.Request;
import spark.Route;

public class ApartmentController {

	public static final Route details = (Request req, Response res) -> {
		try (AutoCloseableDb db = new AutoCloseableDb()) {
			Map<String, Object> model = new HashMap<String, Object>();
			Apartment apartment = Apartment.findById(Integer.parseInt(req.params("id")));
			model.put("apartment", apartment);
			return FreeMarkerRenderer.getInstance().render("/apartments/list.html", model);
		}
	};
	
	public static final Route newForm = (Request req, Response res) -> {
		return FreeMarkerRenderer.getInstance().render("/apartments/new-form.html", null);
	};

	public static final Route create = (Request req, Response res) -> {
		Apartment apartment = new Apartment(Integer.parseInt(req.queryParams("rent")),
											Integer.parseInt( req.queryParams("numberOfBedrooms")), 
											Integer.parseInt(req.queryParams("numberOfBathrooms")), 
											Integer.parseInt(req.queryParams("squareFootage")), 
											req.queryParams("address"), 
											req.queryParams("city"), 
											req.queryParams("state"), 
											Integer.parseInt(req.queryParams("zipCode")));
		try(AutoCloseableDb db = new AutoCloseableDb()) {
			apartment.saveIt();
		}
		res.redirect("/");
		return "";
	};

}
