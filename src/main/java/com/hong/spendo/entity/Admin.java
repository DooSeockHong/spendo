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
 * 관리자 테이블
 */


@Data
@Entity
@Table(name = "admin")
public class Admin {

	// 관리자 일련번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_no")
    private Long adminNo;

    // 관리자 ID
    @Column(name = "admin_id")
    private String adminId;

    // 관리자 비밀번호
    @Column(name = "admin_pw")
    private String adminPw;

    // 관리자 이름
    @Column(name = "admin_name")
    private String adminName;

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
