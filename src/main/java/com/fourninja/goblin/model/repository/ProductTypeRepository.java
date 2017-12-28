
package com.fourninja.goblin.model.repository;

import com.fourninja.goblin.model.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository
    extends JpaRepository<ProductType, Long>
{


}
