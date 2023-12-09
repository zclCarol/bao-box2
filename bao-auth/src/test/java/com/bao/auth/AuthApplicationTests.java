package com.bao.auth;

import com.bao.auth.model.User;
import com.bao.auth.model.UserLoginForm;
import com.bao.auth.service.UserService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthApplicationTests {

	@Autowired
	UserService userService;
	@Test
	void contextLoads() {
		User user = userService.getUserByUserId("LGG00951");
		user.setUserPassword("it123456");

		UserLoginForm uf = new UserLoginForm();
		uf.setUserId(user.getUserId());
		uf.setPassword(user.getUserPassword());

		userService.login(uf);


	}

}
