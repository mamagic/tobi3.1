package com.intheeast.springframe.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.intheeast.springframe.domain.User;

public class UserDaoTest {	
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {				
		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();	
		assertEquals(dao.getCount(), 0);
		
		User user = new User();
		user.setId("gyumee");
<<<<<<< HEAD:vol1/ch2/SpringFrameProj2-3-2/src/com/kitec/springframe/dao/UserDaoTest.java
		user.setName("");
=======
		user.setName("박성철");
>>>>>>> 19b2387d8f021ca25c43995e04fe2b1f2803e355:vol1/ch2/SpringFrameProj2-3-2/src/com/intheeast/springframe/dao/UserDaoTest.java
		user.setPassword("springno1");

		dao.add(user);
		assertEquals(dao.getCount(), 1);
		
		User user2 = dao.get(user.getId());
		
		
		assertEquals(user2.getName(), user.getName());
		assertEquals(user2.getPassword(), user.getPassword());		
	}   

}
