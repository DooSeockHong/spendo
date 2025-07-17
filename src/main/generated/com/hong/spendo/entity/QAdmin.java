package com.hong.spendo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdmin is a Querydsl query type for Admin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdmin extends EntityPathBase<Admin> {

    private static final long serialVersionUID = -2135598272L;

    public static final QAdmin admin = new QAdmin("admin");

    public final StringPath adminId = createString("adminId");

    public final StringPath adminName = createString("adminName");

    public final NumberPath<Long> adminNo = createNumber("adminNo", Long.class);

    public final StringPath adminPw = createString("adminPw");

    public final DatePath<java.time.LocalDate> cretDt = createDate("cretDt", java.time.LocalDate.class);

    public final StringPath delAt = createString("delAt");

    public final DatePath<java.time.LocalDate> updDt = createDate("updDt", java.time.LocalDate.class);

    public QAdmin(String variable) {
        super(Admin.class, forVariable(variable));
    }

    public QAdmin(Path<? extends Admin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdmin(PathMetadata metadata) {
        super(Admin.class, metadata);
    }

}

