package com.fourninja.goblin.model.entity;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProductType is a Querydsl query type for ProductType
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProductType extends EntityPathBase<ProductType> {

    private static final long serialVersionUID = -1455714493L;

    public static final QProductType productType = new QProductType("productType");

    public final BooleanPath actFlag = createBoolean("actFlag");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath createdBy = createString("createdBy");

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final SetPath<Product, QProduct> products = this.<Product, QProduct>createSet("products", Product.class, QProduct.class, PathInits.DIRECT2);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final StringPath updatedBy = createString("updatedBy");

    public QProductType(String variable) {
        super(ProductType.class, forVariable(variable));
    }

    public QProductType(Path<? extends ProductType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductType(PathMetadata<?> metadata) {
        super(ProductType.class, metadata);
    }

}

