package com.intheeast.springframe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {
	
	String driver;
	String url;
	String username;
	String password;
	
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
<<<<<<< HEAD:vol1/ch1/SpringFrameProj1-6-4/src/com/kitec/springframe/dao/DConnectionMaker.java
		Class.forName(driver);
		Connection c = DriverManager.getConnection(url, 
				username,
				password);
=======
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/sbdt_db1?characterEncoding=UTF-8", 
				"root",
				"1234");
>>>>>>> 19b2387d8f021ca25c43995e04fe2b1f2803e355:vol1/ch1/SpringFrameProj1-4-2/src/com/intheeast/springframe/dao/DConnectionMaker.java
		return c;
	}
	

}
