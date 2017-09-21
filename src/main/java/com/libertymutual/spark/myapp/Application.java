package com.libertymutual.spark.myapp;

import static spark.Spark.*;

import com.libertymutual.spark.myapp.controllers.ApartmentController;
import com.libertymutual.spark.myapp.controllers.ActivationController;
import com.libertymutual.spark.myapp.controllers.ApartmentApiController;
import com.libertymutual.spark.myapp.controllers.HomeController;
import com.libertymutual.spark.myapp.controllers.LikeController;
import com.libertymutual.spark.myapp.controllers.SessionController;
import com.libertymutual.spark.myapp.controllers.UserApiController;
import com.libertymutual.spark.myapp.controllers.UserController;
import com.libertymutual.spark.myapp.filters.SecurityFilters;
import com.libertymutual.spark.myapp.utilities.SeedData;

public class Application {
	
	public static void main(String[] args) {
		
		SeedData.create();
		
		before("/*", SecurityFilters.checkIfSessionIsNew);

		get("/", HomeController.index);
		get("/login", SessionController.newForm);
		
		before("/login", SecurityFilters.checkSubmittedCsrfToken);
		post("/login", SessionController.create);
		get("logout", SessionController.destroy);
		
		path("/apartments", () -> {
			get("/new", ApartmentController.newForm);
			
			before("", SecurityFilters.checkSubmittedCsrfToken);
			before("", SecurityFilters.isAuthenticated);
			post("", ApartmentController.create);
			
			get("/mine", ApartmentController.index);
			get("/:id", ApartmentController.details);
			
			before("/:id/like", SecurityFilters.checkSubmittedCsrfToken);
			before("/:id/like", SecurityFilters.isAuthenticated);
			post("/:id/like", LikeController.create);
			
			before("/:id/deactivations", SecurityFilters.checkSubmittedCsrfToken);
			before("/:id/deactivations", SecurityFilters.isAuthenticated);
			post("/:id/deactivations", ActivationController.update);
			
			before("/:id/activations", SecurityFilters.checkSubmittedCsrfToken);
			before("/:id/activations", SecurityFilters.isAuthenticated);
			post("/:id/activations", ActivationController.update);
		});
		
		path("/users", () -> {
			get("/new", UserController.newForm);
			before("", SecurityFilters.checkSubmittedCsrfToken);
			post("", UserController.create);
		});
		
			
		path("/api", () -> {
			get("/apartments/:id", ApartmentApiController.details);
			
			before("/apartments", SecurityFilters.checkSubmittedCsrfToken);
			post("/apartments", ApartmentApiController.create);
			
			get("/users/:id", UserApiController.details);
			
			before("/apartments", SecurityFilters.checkSubmittedCsrfToken);
			post("/users", UserApiController.create);
		});
	}
	
}
