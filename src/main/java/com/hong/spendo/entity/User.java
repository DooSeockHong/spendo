package com.hong.spendo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 
 * 사용자 테이블
 */


@Data
@Entity
@Table(name = "user")
public class User {

	// 사용자 일련번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    // 이메일(ID)
    @Column(name = "user_email")
    private String userEmail;

    // 비밀번호
    @Column(name = "user_pw")
    private String userPw;

    // 닉네임
    @Column(name = "user_name")
    private String userName;

    // 삭제 여부
    @Column(name = "del_at")
    private String delAt;

    // 생성날짜
    @Column(name = "cret_dt")
    private LocalDate cretDt = LocalDate.now(); 

    // 수정날짜
    @Column(name = "upd_dt")
    private LocalDate updDt;
	
	
}
