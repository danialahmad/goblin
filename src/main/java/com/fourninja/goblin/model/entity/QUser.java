package com.fourninja.goblin.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 992285329L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final StringPath email = createString("email");

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath firstname = createString("firstname");

    public final StringPath lastIp = createString("lastIp");

    public final DateTimePath<java.util.Date> lastLogin = createDateTime("lastLogin", java.util.Date.class);

    public final StringPath lastname = createString("lastname");

    public final DateTimePath<java.util.Date> lastPasswordResetDate = createDateTime("lastPasswordResetDate", java.util.Date.class);

    public final StringPath password = createString("password");

    public final StringPath timezone = createString("timezone");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final StringPath updatedBy = createString("updatedBy");

    public final StringPath username = createString("username");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata<?> metadata) {
        super(User.class, metadata);
    }

}

