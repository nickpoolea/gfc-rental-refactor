package com.libertymutual.spark.myapp.utilities;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

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
	
	public String render(String templatePath, Map<String, Object> model) {
		Template temp = null;
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
