package com.libertymutual.spark.myapp.controllers;

import com.libertymutual.spark.myapp.models.Apartment;
import com.libertymutual.spark.myapp.utilities.AutoCloseableDb;

import spark.Request;
import spark.Response;
import spark.Route;

public class ActivationController {
	
	public static final Route update = (Request req, Response res) -> {
		try(AutoCloseableDb db = new AutoCloseableDb()) {
			boolean activationStatus = Boolean.valueOf(req.queryParams("activationStatus"));
			Apartment apartment = Apartment.findById(Integer.parseInt(req.params("id")));
			System.out.println("AS: " + activationStatus );
			apartment.setIsActive(activationStatus);
			apartment.saveIt();
		}
		res.redirect("/apartments/" + req.params("id"));
		return "";
	};

}
