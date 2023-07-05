package com.intheeast.springframe.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.intheeast.springframe.domain.User;

<<<<<<< HEAD:vol1/ch2/SpringFrameProj2-5/src/com/kitec/springframe/dao/UserDaoTest.java

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {TestDaoFactory.class})
public class UserDaoTest { 
	
//	@Autowired
//	ApplicationContext context;
//	
//	@Autowired
	UserDao dao;
=======
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDaoFactory.class})
public class UserDaoTest {
	
	//@Autowired
	//private ApplicationContext context;	
	 
	@Autowired
	private UserDao dao;
>>>>>>> 19b2387d8f021ca25c43995e04fe2b1f2803e355:vol1/ch3/SpringFrameProj3-4-2/src/com/kitec/springframe/dao/UserDaoTest.java
	private User user1;
	private User user2;
	private User user3;
	
		
	@BeforeEach
	public void setUp() {	
<<<<<<< HEAD:vol1/ch2/SpringFrameProj2-5/src/com/kitec/springframe/dao/UserDaoTest.java
		UserDao dao = new UserDao();
		dao.setDataSource(setDataSource());
		//this.dao = this.context.getBean("userDao", UserDao.class);
		user1 = new User("user1", "sungkim", "5678");
		user2 = new User("user2", "brucelee", "9012");
		user3 = new User("user3", "haechoi", "1234");
=======
		
		user1 = new User("user1", "sungkim", "0000");
		user2 = new User("user2", "brucelee", "1111");
		user3 = new User("user3", "haechoi", "2222");
>>>>>>> 19b2387d8f021ca25c43995e04fe2b1f2803e355:vol1/ch3/SpringFrameProj3-4-2/src/com/kitec/springframe/dao/UserDaoTest.java
	}
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {				

		UserDao dao = new UserDao();
		dao.setDataSource(setDataSource());
		
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
		
		dao.add(user1);
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		User userget1 = dao.get(user1.getId());
		assertEquals(user1.getName(), userget1.getName());
		assertEquals(user1.getPassword(), userget1.getPassword());
		
		User userget2 = dao.get(user2.getId());		
		assertEquals(user2.getName(), userget2.getName());
		assertEquals(user2.getPassword(), userget2.getPassword());		
	}
	
	@Test
	public void count() throws SQLException, ClassNotFoundException {		
		UserDao dao = new UserDao();
		dao.setDataSource(setDataSource());

		dao.deleteAll();
		assertEquals(dao.getCount(), 0);

		dao.add(user1);
		assertEquals(dao.getCount(), 1);
		
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		dao.add(user3);
		assertEquals(dao.getCount(), 3);		
	}
	
	@Test
	public void getUserFailure() throws SQLException, ClassNotFoundException {		

		UserDao dao = new UserDao();
		dao.setDataSource(setDataSource());
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);		
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, 
				() -> {dao.get("unknown_id");});	
	}	
	
	@Test
	public void update() throws SQLException, ClassNotFoundException {
		UserDao dao = new UserDao();
		dao.setDataSource(setDataSource());

		dao.deleteAll();
		User user = new User("user1", "ryuhyunwoo", "1234");
		
		dao.add(user);
		dao.update(user);
		
		User user1 = dao.get("user1");
		System.out.println(user1.getName());
		assertEquals(user.getName(), user1.getName());		
	}
	
	public SimpleDriverDataSource setDataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost:3306/sbdt_db1?characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("QWERzxc!@#1234");
		return dataSource;
	}

}
