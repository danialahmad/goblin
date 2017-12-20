
package com.fourninja.goblin.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fourninja.goblin.model.entity.User;

public interface UserRepository
    extends JpaRepository<User, String>
{


}
