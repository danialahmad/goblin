package com.fourninja.goblin.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fourninja.goblin.model.entity.User;
import com.fourninja.goblin.model.repository.UserRepository;
import com.fourninja.goblin.security.jwt.JwtUserFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOne(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }

}
