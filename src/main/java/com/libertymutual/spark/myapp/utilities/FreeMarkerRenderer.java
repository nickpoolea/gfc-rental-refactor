package com.libertymutual.spark.myapp.utilities;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.libertymutual.spark.myapp.models.User;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import spark.Request;

public class FreeMarkerRenderer {
	
	private Configuration config;
	private static final FreeMarkerRenderer instance = new FreeMarkerRenderer("C:\\Users\\n0292073\\dev\\com.libertymutual.spark.myapp\\src\\main\\resources\\templates");

	private FreeMarkerRenderer(String folderName) {

		config = new Configuration(Configuration.VERSION_2_3_0);
		try {
			config.setDirectoryForTemplateLoading(new File(folderName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		config.setDefaultEncoding("UTF-8");
		config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		config.setLogTemplateExceptions(false);
	}
	
	public static FreeMarkerRenderer getInstance() {
		return instance;
	}
	
	public String render(String templatePath, Map<String, Object> model, Request req) {
		Template temp = null;
		User currentUser = req.session().attribute("currentUser");
		String csrf_token = req.session().attribute("csrf_token");
		
		if (model == null) {
			model = new HashMap<String, Object>();
		}
		
		model.put("csrf_token", csrf_token);
		
		if (currentUser != null) {
			model.put("currentUser", currentUser);
		}
			
		try {
			temp = config.getTemplate(templatePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringWriter writer = new StringWriter();
		try {
			temp.process(model, writer);
		} catch (TemplateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer.toString();
	}

}
