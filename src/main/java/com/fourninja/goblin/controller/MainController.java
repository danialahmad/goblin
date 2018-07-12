package com.fourninja.goblin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fourninja.goblin.form.response.authentication.AuthenticationResponse;
import com.fourninja.goblin.service.UserService;

@RestController
public class MainController {

	@Value("${db.username}")
	private String username;
	
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index(){
	//	datasourceconfigRepository.findAll().forEach(c-> System.out.println(c.getName()));
		
		userService.getAllUsers().forEach(user->System.out.println(user.getUsername()));
		return "Greeting! ";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ResponseEntity<?>  test(){
	//	datasourceconfigRepository.findAll().forEach(c-> System.out.println(c.getName()));
		
		userService.getAllUsers().forEach(user->System.out.println(user.getUsername()));
		return ResponseEntity.ok("test saja");
	}
}
