package com.libertymutual.spark.myapp;

import org.mindrot.jbcrypt.BCrypt;


import static spark.Spark.*;

import com.libertymutual.spark.myapp.controllers.ApartmentController;
import com.libertymutual.spark.myapp.controllers.ApartmentApiController;
import com.libertymutual.spark.myapp.controllers.HomeController;
import com.libertymutual.spark.myapp.controllers.SessionController;
import com.libertymutual.spark.myapp.controllers.UserApiController;
import com.libertymutual.spark.myapp.controllers.UserController;
import com.libertymutual.spark.myapp.filters.SecurityFilters;
import com.libertymutual.spark.myapp.models.Apartment;
import com.libertymutual.spark.myapp.models.User;
import com.libertymutual.spark.myapp.utilities.AutoCloseableDb;

public class Application {
	
	public static void main(String[] args) {
		
		String password = "Password";
		String encrytedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		
		try (AutoCloseableDb db = new AutoCloseableDb()) {
			User.deleteAll();
			new User("NPA", encrytedPassword, "Nick", "Poole").saveIt();
			
			Apartment.deleteAll();
			new Apartment(2000, 3, 1, 1000, "123 Main", "Honolulu", "Hi", 96825).saveIt();
			new Apartment(1500, 2, 2, 750, "123 Koko Isle", "Honolulu", "Hi", 96825).saveIt();
		}
		
		get("/",               HomeController.index);
		
		path("/apartments", () -> {
			before("/new", SecurityFilters.isAuthenticated);
			get("/new", ApartmentController.newForm);
			
			before("", SecurityFilters.isAuthenticated);
			post("",    ApartmentController.create);
			
			get("/:id", ApartmentController.details);
		});
		
		
		get("/login",          SessionController.newForm);
		post("/login",         SessionController.create);
		get("logout",          SessionController.destroy);
		
		get("signup",          UserController.newForm);
		post("signup",         UserController.create);
			
		path("/api", () -> {
			get("/apartments/:id", ApartmentApiController.details);
			post("/apartments",    ApartmentApiController.create);
			get("/users/:id",      UserApiController.details);
			post("/users",         UserApiController.create);
		});
	}
	
}
