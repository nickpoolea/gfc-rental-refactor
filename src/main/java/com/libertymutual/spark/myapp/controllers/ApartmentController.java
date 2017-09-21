package com.libertymutual.spark.myapp.controllers;

import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.libertymutual.spark.myapp.models.Apartment;
import com.libertymutual.spark.myapp.models.ApartmentsUsers;
import com.libertymutual.spark.myapp.models.User;
import com.libertymutual.spark.myapp.utilities.AutoCloseableDb;
import com.libertymutual.spark.myapp.utilities.FreeMarkerRenderer;

import spark.Request;
import spark.Route;

public class ApartmentController {

	public static final Route details = (Request req, Response res) -> {
	
		try (AutoCloseableDb db = new AutoCloseableDb()) {
			Map<String, Object> model = new HashMap<String, Object>();
			Apartment apartment = Apartment.findById(Integer.parseInt(req.params("id")));
			User currentUser = req.session().attribute("currentUser");
			List<User> usersThatLikedApartment = apartment.getAll(User.class);
			int numberOfLikes = usersThatLikedApartment.size();
			int numberOfCurrentUserLikes = 0;
			if (currentUser != null) {
				List<User> currentUserLikes = ApartmentsUsers.where("user_id = ? and apartment_id = ?", currentUser.getId(), apartment.getId());
				numberOfCurrentUserLikes = currentUserLikes.size();
			}
			
			model.put("numberOfLikes", numberOfLikes);
			model.put("usersThatLikedApartment", usersThatLikedApartment);
			model.put("numberOfCurrentUserLikes", numberOfCurrentUserLikes);
			model.put("apartment", apartment);
			return FreeMarkerRenderer.getInstance().render("/apartments/list.html", model, req);
		}
	};
	
	public static final Route newForm = (Request req, Response res) -> {
		return FreeMarkerRenderer.getInstance().render("/apartments/new-form.html", null, req);
	};

	public static final Route create = (Request req, Response res) -> {
		Apartment apartment = new Apartment(Integer.parseInt(req.queryParams("rent")),
											Integer.parseInt( req.queryParams("number_of_bedrooms")), 
											Integer.parseInt(req.queryParams("number_of_bathrooms")), 
											Integer.parseInt(req.queryParams("square_footage")), 
											req.queryParams("address"), 
											req.queryParams("city"), 
											req.queryParams("state"), 
											Integer.parseInt(req.queryParams("zip_code")),
											true);
		try(AutoCloseableDb db = new AutoCloseableDb()) {
			User user = req.session().attribute("currentUser");
			user.add(apartment);
			apartment.saveIt();
		}
		res.redirect("/");
		return "";
	};

	public static Route index = (Request req, Response res) -> {
		User user = req.session().attribute("currentUser");
		long id = (long) user.getId();
		
		try(AutoCloseableDb db = new AutoCloseableDb()) {
			List<Apartment> activeApartments = Apartment.where("user_id = ? and is_active = ?", id, true);
			List<Apartment> inactiveApartments = Apartment.where("user_id = ? and is_active = ?", id, false);
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("activeApartments", activeApartments);
			model.put("inactiveApartments", inactiveApartments);
			return FreeMarkerRenderer.getInstance().render("/apartments/index.html", model, req);
		}
		
	};

}
