package com.fourninja.goblin.security.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fourninja.goblin.model.entity.User;

public final class JwtUserFactory {

	private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                new ArrayList<GrantedAuthority>(),
                user.isEnabled(),
                user.getLastPasswordResetDate()
        );
    }

//    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
//        return authorities.stream()
//                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
//                .collect(Collectors.toList());
//    }
	
}
