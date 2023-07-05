package com.kitec.springframe.dao;

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
		Class.forName(driver);
		Connection c = DriverManager.getConnection(url, 
				username,
				password);
		return c;
	}
	

}
