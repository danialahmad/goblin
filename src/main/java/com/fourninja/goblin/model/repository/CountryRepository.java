
package com.fourninja.goblin.model.repository;

import com.fourninja.goblin.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository
    extends JpaRepository<Country, Long>
{


}
