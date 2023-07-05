package com.kitec.springframe.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {
	
	@Bean
	public DataSource dataSource() {
		
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost:3306/sbdt_db1?characterEncoding=UTF-8");
		dataSource.setUsername("root");
<<<<<<< HEAD:vol1/ch2/SpringFrameProj2-3-3/src/com/kitec/springframe/dao/DaoFactory.java
		dataSource.setPassword("QWERzxc!@#1234");

=======
		dataSource.setPassword("1234");
		
>>>>>>> 19b2387d8f021ca25c43995e04fe2b1f2803e355:vol1/ch3/SpringFrameProj3-6-1/src/com/kitec/springframe/dao/DaoFactory.java
		return dataSource;
	}
	
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}

}
