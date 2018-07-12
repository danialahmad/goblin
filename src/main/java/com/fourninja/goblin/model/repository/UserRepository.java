
package com.fourninja.goblin.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourninja.goblin.model.entity.User;

public interface UserRepository
    extends JpaRepository<User, String>
{
	
	List<User> findByEnabledTrue();

}
