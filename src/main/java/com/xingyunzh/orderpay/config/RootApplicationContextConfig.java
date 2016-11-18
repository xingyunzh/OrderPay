package com.xingyunzh.orderpay.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan("com.xingyunzh.orderpay")
@ImportResource("classpath:spring-hibernate.xml")
public class RootApplicationContextConfig {
//	@Bean
//	public DataSource dataSource(){
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
//				.addScript("db/sql/create-table.sql")
//				.addScript("db/sql/insert-data.sql")
//				.build();
//		return db;
//	}
	
//	@Bean
//	public NamedParameterJdbcTemplate getJdbcTemplate(){
//		return new NamedParameterJdbcTemplate(dataSource());
//	}
}
