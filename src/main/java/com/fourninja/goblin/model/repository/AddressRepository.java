
package com.fourninja.goblin.model.repository;

import com.fourninja.goblin.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository
    extends JpaRepository<Address, Long>
{


}
