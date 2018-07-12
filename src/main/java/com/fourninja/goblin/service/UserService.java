package com.fourninja.goblin.service;

import java.util.List;

import com.fourninja.goblin.form.request.authentication.UserRequest;
import com.fourninja.goblin.model.entity.User;

public interface UserService {

	public List<User> getAllUsers();
	public boolean registerNewUser(UserRequest userRequest);
}
