package com.sample;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.sample.repository.PersonRepositoryNoSql;
import com.sample.repository.PersonRepositorySql;

@Configuration
public class BeansConfiguration {

	@Bean public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setType(EmbeddedDatabaseType.H2).build();
	}

	@Bean public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}
	
	@Bean public PersonRepositorySql personRepositorySql(){
		return new PersonRepositorySql();
	}
	
	@Bean public PersonRepositoryNoSql personRepositoryNoSql(){
		return new PersonRepositoryNoSql();
	}
}
