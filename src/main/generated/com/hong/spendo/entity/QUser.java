package com.hong.spendo.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 347361722L;

    public static final QUser user = new QUser("user");

    public final DatePath<java.time.LocalDate> cretDt = createDate("cretDt", java.time.LocalDate.class);

    public final StringPath delAt = createString("delAt");

    public final DatePath<java.time.LocalDate> updDt = createDate("updDt", java.time.LocalDate.class);

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userName = createString("userName");

    public final NumberPath<Long> userNo = createNumber("userNo", Long.class);

    public final StringPath userPw = createString("userPw");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

