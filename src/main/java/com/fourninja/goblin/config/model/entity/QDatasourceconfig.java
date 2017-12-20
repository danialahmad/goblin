package com.fourninja.goblin.config.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QDatasourceconfig is a Querydsl query type for Datasourceconfig
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QDatasourceconfig extends EntityPathBase<Datasourceconfig> {

    private static final long serialVersionUID = -1008056230L;

    public static final QDatasourceconfig datasourceconfig = new QDatasourceconfig("datasourceconfig");

    public final StringPath driverclassname = createString("driverclassname");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath url = createString("url");

    public final StringPath username = createString("username");

    public QDatasourceconfig(String variable) {
        super(Datasourceconfig.class, forVariable(variable));
    }

    public QDatasourceconfig(Path<? extends Datasourceconfig> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDatasourceconfig(PathMetadata<?> metadata) {
        super(Datasourceconfig.class, metadata);
    }

}

