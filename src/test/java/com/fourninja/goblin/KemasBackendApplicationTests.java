package com.fourninja.goblin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KemasBackendApplicationTests {

	@Test
	public void contextLoads() {
		PasswordEncoder enc=new BCryptPasswordEncoder();
		String password=enc.encode("password");
		System.out.println(password);
	}

}
