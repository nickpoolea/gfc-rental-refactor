package com.libertymutual.spark.myapp.filters;

import static spark.Spark.halt;

import java.util.UUID;

import spark.Filter;
import spark.Request;
import spark.Response;

public class SecurityFilters {

	public static Filter isAuthenticated = (Request req, Response res) -> {
		if (req.session().attribute("currentUser") == null) {
			res.redirect("/login?returnPath=" + req.pathInfo());
			halt();
		}
	};

	public static final Filter checkIfSessionIsNew = (Request req, Response res) -> {
		if (req.session().isNew()) {
			String uuid = UUID.randomUUID().toString();
			req.session().attribute("csrf_token", uuid);
		}

	};

	public static final Filter checkSubmittedCsrfToken = (Request req, Response res) -> {

		if (req.requestMethod() == "POST") {
			String serverToken = req.session().attribute("csrf_token");
			String submittedToken = req.queryParams("csrf");

			if (!serverToken.equals(submittedToken)) {
				res.redirect("/");
				halt("CSRF not authenticated");
			} 
		}
	};
}
