package com.hong.spendo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSpendo is a Querydsl query type for Spendo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSpendo extends EntityPathBase<Spendo> {

    private static final long serialVersionUID = -1252863744L;

    public static final QSpendo spendo = new QSpendo("spendo");

    public final DatePath<java.time.LocalDate> cretDt = createDate("cretDt", java.time.LocalDate.class);

    public final StringPath delAt = createString("delAt");

    public final StringPath spendoCodeType = createString("spendoCodeType");

    public final StringPath spendoContent = createString("spendoContent");

    public final NumberPath<Long> spendoNo = createNumber("spendoNo", Long.class);

    public final NumberPath<Integer> spendoPrice = createNumber("spendoPrice", Integer.class);

    public final StringPath spendoTitle = createString("spendoTitle");

    public final StringPath spendoType = createString("spendoType");

    public final DatePath<java.time.LocalDate> updDt = createDate("updDt", java.time.LocalDate.class);

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public QSpendo(String variable) {
        super(Spendo.class, forVariable(variable));
    }

    public QSpendo(Path<? extends Spendo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSpendo(PathMetadata metadata) {
        super(Spendo.class, metadata);
    }

}

