package com.sample;

import static java.lang.System.getProperty;

import org.flywaydb.core.Flyway;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StartupCallback implements ApplicationListener<ContextRefreshedEvent>{
	
	public static final String URL = "jdbc:h2:"+getProperty("user.dir")+"/target/spring-example";
	public static final String USERNAME = "";
	public static final String PASSWORD = "";
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Flyway flyway = new Flyway();
		flyway.setDataSource(URL, USERNAME, PASSWORD);
		flyway.migrate();
	}
}
