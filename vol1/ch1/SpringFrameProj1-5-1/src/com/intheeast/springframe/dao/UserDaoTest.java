package com.intheeast.springframe.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.intheeast.springframe.domain.User;


public class UserDaoTest {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(DaoFactory.class);
		
		System.out.println("hello world");
		
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
<<<<<<< HEAD:vol1/ch1/SpringFrameProj1-6-4/src/com/kitec/springframe/dao/UserDaoTest.java
		user.setId("1234");
		user.setName("류현우");
=======
		user.setId("whiteship");
		user.setName("백기선");
>>>>>>> 19b2387d8f021ca25c43995e04fe2b1f2803e355:vol1/ch1/SpringFrameProj1-5-1/src/com/intheeast/springframe/dao/UserDaoTest.java
		user.setPassword("married");

		dao.add(user);
			
<<<<<<< HEAD:vol1/ch1/SpringFrameProj1-6-4/src/com/kitec/springframe/dao/UserDaoTest.java
		System.out.println(user.getId());
=======
		System.out.println(user.getId() + "\n등록 성공");
>>>>>>> 19b2387d8f021ca25c43995e04fe2b1f2803e355:vol1/ch1/SpringFrameProj1-5-1/src/com/intheeast/springframe/dao/UserDaoTest.java
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
			
<<<<<<< HEAD:vol1/ch1/SpringFrameProj1-6-4/src/com/kitec/springframe/dao/UserDaoTest.java
		System.out.println(user2.getId());
=======
		System.out.println(user2.getId() + "\n조회 성공");
>>>>>>> 19b2387d8f021ca25c43995e04fe2b1f2803e355:vol1/ch1/SpringFrameProj1-5-1/src/com/intheeast/springframe/dao/UserDaoTest.java
	}

}
