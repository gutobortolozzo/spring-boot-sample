package com.objective;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.sample.Startup;
import com.sample.StartupCallback;

@Ignore
@Transactional
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Startup.class, StartupCallback.class, BeanSupportInjection.class})
public class AbstractSpringConfiguration {
	
	protected MockMvc mockMvc;
}
