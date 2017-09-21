package com.libertymutual.spark.myapp.controllers;

import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import com.libertymutual.spark.myapp.models.User;
import com.libertymutual.spark.myapp.utilities.AutoCloseableDb;
import com.libertymutual.spark.myapp.utilities.FreeMarkerRenderer;

import spark.Request;
import spark.Response;
import spark.Route;

public class SessionController {

	public static final Route newForm = (Request req, Response res) -> {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("returnPath", req.queryParams("returnPath"));
		return FreeMarkerRenderer.getInstance().render("/session/login.html", model, req);
	};
	
	public static final Route create = (Request req, Response res) -> {
		String email = req.queryParams("email");
		String password = req.queryParams("password");
		
		try (AutoCloseableDb db = new AutoCloseableDb()) {
			User user = User.findFirst("email = ?", email);
			if (user != null && BCrypt.checkpw(password, user.getPassword())) {
				req.session().attribute("currentUser", user);
			}
		}
		res.redirect(req.queryParamOrDefault("returnPath", "/"));
		return "";
	};
	
	public static final Route destroy = (Request req, Response res) -> {
		req.session().removeAttribute("currentUser");
		res.redirect("/");
		return "";
	};

}
