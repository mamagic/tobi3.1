package com.intheeast.springframe.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
	
	@Bean
<<<<<<< HEAD:vol1/ch1/SpringFrameProj1-6-4/src/com/kitec/springframe/dao/DaoFactory.java
	public UserDao userDao() {
		UserDao dao = new UserDao();
		dao.setConnectionMaker(connectionMaker());
		return dao;
	}	
	
	@Bean
	public ConnectionMaker connectionMaker() {
		DConnectionMaker maker = new DConnectionMaker();
		maker.setDriver("com.mysql.cj.jdbc.Driver");
		maker.setUrl("jdbc:mysql://localhost:3306/sbdt_db1?characterEncoding=UTF-8");
		maker.setUsername("root");
		maker.setPassword("QWERzxc!@#1234");
		return maker;
=======
	public UserDao userDao() {		
		return new UserDao(connectionMaker());
	}	
	
	@Bean
	public ConnectionMaker connectionMaker() {		
		return new DConnectionMaker();
>>>>>>> 19b2387d8f021ca25c43995e04fe2b1f2803e355:vol1/ch1/SpringFrameProj1-5-1/src/com/intheeast/springframe/dao/DaoFactory.java
	}
	
	

}
