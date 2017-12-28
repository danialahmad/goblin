
package com.fourninja.goblin.model.repository;

import com.fourninja.goblin.model.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository
    extends JpaRepository<Agent, Long>
{


}
