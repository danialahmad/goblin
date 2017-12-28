package com.fourninja.goblin.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAgentType is a Querydsl query type for AgentType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAgentType extends EntityPathBase<AgentType> {

    private static final long serialVersionUID = 779444441L;

    public static final QAgentType agentType = new QAgentType("agentType");

    public final SetPath<Agent, QAgent> agents = this.<Agent, QAgent>createSet("agents", Agent.class, QAgent.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final StringPath updatedBy = createString("updatedBy");

    public QAgentType(String variable) {
        super(AgentType.class, forVariable(variable));
    }

    public QAgentType(Path<? extends AgentType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAgentType(PathMetadata<?> metadata) {
        super(AgentType.class, metadata);
    }

}

