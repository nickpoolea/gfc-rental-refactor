package com.libertymutual.spark.myapp.controllers;

import org.mindrot.jbcrypt.BCrypt;

import com.libertymutual.spark.myapp.models.User;
import com.libertymutual.spark.myapp.utilities.AutoCloseableDb;
import com.libertymutual.spark.myapp.utilities.FreeMarkerRenderer;

import spark.Request;
import spark.Response;
import spark.Route;

public class UserController {
	
	public static Route newForm = (Request req, Response res) -> {
		return FreeMarkerRenderer.getInstance().render("/user/signup.html", null);
	};

	public static Route create = (Request req, Response res) -> {
		String email = req.queryParams("email");
		String password = BCrypt.hashpw(req.queryParams("password"), BCrypt.gensalt());
		String firstName = req.queryParams("firstName");
		String lastName = req.queryParams("lastName");
		
		User user  = new User (email, password, firstName, lastName);
		
		if (user != null) {
			
			try (AutoCloseableDb db = new AutoCloseableDb()) {
				user.saveIt();
				req.session().attribute("currentUser", user);
			}
		}
		res.redirect("/");
		return "";
	};

}
