package com.fourninja.goblin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fourninja.goblin.form.request.authentication.UserRequest;
import com.fourninja.goblin.model.entity.User;
import com.fourninja.goblin.model.repository.UserRepository;
import com.fourninja.goblin.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired	
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findByEnabledTrue();
	}

	@Override
	public boolean registerNewUser(UserRequest userRequest) {
		User user  =new User();
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());
		user.setFirstname(userRequest.getFirstname());
		user.setLastname(userRequest.getLastname());
		user.setEmail(userRequest.getEmail());
		userRepository.save(user);
		return false;
	}

}
