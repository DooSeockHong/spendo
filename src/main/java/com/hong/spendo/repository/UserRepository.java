package com.hong.spendo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hong.spendo.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
