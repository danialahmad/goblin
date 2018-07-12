package com.fourninja.goblin.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QAgent is a Querydsl query type for Agent
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QAgent extends EntityPathBase<Agent> {

    private static final long serialVersionUID = 677246207L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAgent agent = new QAgent("agent");

    public final BooleanPath actFlag = createBoolean("actFlag");

    public final QAddress address;

    public final StringPath agentCode = createString("agentCode");

    public final QAgentType agentType;

    public final QCountry country;

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath idNumber = createString("idNumber");

    public final StringPath name = createString("name");

    public final BooleanPath onlineMode = createBoolean("onlineMode");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final SetPath<User, QUser> users = this.<User, QUser>createSet("users", User.class, QUser.class, PathInits.DIRECT2);

    public QAgent(String variable) {
        this(Agent.class, forVariable(variable), INITS);
    }

    public QAgent(Path<? extends Agent> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAgent(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QAgent(PathMetadata<?> metadata, PathInits inits) {
        this(Agent.class, metadata, inits);
    }

    public QAgent(Class<? extends Agent> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new QAddress(forProperty("address"), inits.get("address")) : null;
        this.agentType = inits.isInitialized("agentType") ? new QAgentType(forProperty("agentType")) : null;
        this.country = inits.isInitialized("country") ? new QCountry(forProperty("country")) : null;
    }

}

