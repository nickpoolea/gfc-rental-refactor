package com.libertymutual.spark.myapp.controllers;

import org.javalite.activejdbc.Model;

import com.libertymutual.spark.myapp.models.Apartment;
import com.libertymutual.spark.myapp.models.User;
import com.libertymutual.spark.myapp.utilities.AutoCloseableDb;

import spark.Request;
import spark.Response;
import spark.Route;

public class LikeController extends Model {

	public static final Route create = (Request req, Response res) -> {
		try (AutoCloseableDb db = new AutoCloseableDb()) {
			User user = req.session().attribute("currentUser");
			Apartment apartment = Apartment.findById(Long.valueOf(req.params("id")));
			apartment.add(user);
		}
		res.redirect("/apartments/" + req.params("id"));
		return "";
	};

}
