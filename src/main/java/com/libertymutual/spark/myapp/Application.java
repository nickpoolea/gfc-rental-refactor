package com.libertymutual.spark.myapp;

import static spark.Spark.*;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.libertymutual.spark.myapp.controllers.ApartmentController;
import com.libertymutual.spark.myapp.controllers.HomeController;
import com.libertymutual.spark.myapp.controllers.SessionController;
import com.libertymutual.spark.myapp.utilities.MustacheRenderer;

public class Application {
	
	public static void main(String[] args) {
		get("/",               HomeController.index);
		get("/apartments/:id", ApartmentController.details);
		get("/login",          SessionController.newForm);
		post("/login",         SessionController.create);
	}
	
}
