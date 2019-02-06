package com.stargazerproject.spring.context.impl;

import org.springframework.boot.builder.SpringApplicationBuilder;

import com.stargazerproject.spring.context.GlobalApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

public class GlobalAnnotationApplicationContext extends GlobalApplicationContext{
	
	private GlobalAnnotationApplicationContext() {}
	
	public static void ApplicationContextInitialize(String args[], Class<?>... annotatedClasses){
		ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder().sources(annotatedClasses).run(args);
		GlobalApplicationContextInstance.globalAnnotationApplicationContext.setApplicationContext(configurableApplicationContext);
	}
	
	protected static class GlobalApplicationContextInstance{
		private static GlobalAnnotationApplicationContext globalAnnotationApplicationContext;
		static{
			globalAnnotationApplicationContext = new GlobalAnnotationApplicationContext();
		}
	}
	
}