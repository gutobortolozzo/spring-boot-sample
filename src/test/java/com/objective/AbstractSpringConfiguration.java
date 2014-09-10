package com.objective;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.sample.BeansConfiguration;
import com.sample.MongoConfiguration;
import com.sample.Startup;
import com.sample.StartupCallback;
import com.sample.repository.PersonRepositoryNoSql;

@Ignore
@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Startup.class, StartupCallback.class, BeanSupportInjection.class, 
								 BeansConfiguration.class, MongoConfiguration.class})
public class AbstractSpringConfiguration {
	
	@Autowired private PersonRepositoryNoSql repo;
	
	@BeforeClass public static void setupClass() throws Exception {
		MongoConfiguration.start();
	}
	
	@Before public void setup() throws Exception{
		repo.collections().stream().
			filter(collection -> !collection.startsWith("system")).
			forEach(collection -> repo.truncate(collection));
	}
}
