
package com.fourninja.goblin.model.repository;

import com.fourninja.goblin.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
    extends JpaRepository<Product, Long>
{


}
