package model.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.collaboration.project.dao.UsersDao;
import com.collaboration.project.model.Users;

public class UsersTest {
	private static AnnotationConfigApplicationContext annotationConfigApplicationContext;
	private static UsersDao usersDao;
	private Users users;

	@BeforeClass
	public static void init() {
		annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
		annotationConfigApplicationContext.scan("com.collaboration.project");
		annotationConfigApplicationContext.refresh();
		usersDao = (UsersDao) annotationConfigApplicationContext.getBean("usersDao");

	}

	@Test
	public void createUserTest() {

		users = new Users();
		users.setFirstName("sudhakar");
		users.setLastName("perumala");
		users.setEmail("sudhakar@gmail.com");
		users.setMobile("123456789");
		users.setUserName("sudhakar");
		users.setPassword("123456789");
		users.setCpassword("123456789");
		assertEquals("success", true, usersDao.createUser(users));
	}

}
