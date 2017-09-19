package com.libertymutual.spark.myapp.utilities;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;

public class MustacheRenderer {
	
	private DefaultMustacheFactory factory;
	private static final MustacheRenderer instance = new MustacheRenderer("templates");
	
	private MustacheRenderer(String folderName) {
		factory = new DefaultMustacheFactory(folderName);
	}
	
	public static MustacheRenderer getInstance() {
		return instance;
	}
	
	public String render(String templatePath, Map<String, Object> model) {
		Mustache mustache = factory.compile(templatePath);
		StringWriter writer = new StringWriter();
		mustache.execute(writer, model);
		return writer.toString();
	}

}