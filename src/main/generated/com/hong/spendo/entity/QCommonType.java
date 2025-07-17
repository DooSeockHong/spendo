package com.hong.spendo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommonType is a Querydsl query type for CommonType
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommonType extends EntityPathBase<CommonType> {

    private static final long serialVersionUID = -2119616908L;

    public static final QCommonType commonType = new QCommonType("commonType");

    public final StringPath commonCode = createString("commonCode");

    public final StringPath commonName = createString("commonName");

    public final NumberPath<Long> commonNo = createNumber("commonNo", Long.class);

    public final StringPath delAt = createString("delAt");

    public QCommonType(String variable) {
        super(CommonType.class, forVariable(variable));
    }

    public QCommonType(Path<? extends CommonType> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommonType(PathMetadata metadata) {
        super(CommonType.class, metadata);
    }

}

