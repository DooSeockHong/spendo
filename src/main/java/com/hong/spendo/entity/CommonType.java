package com.hong.spendo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 
 * 공통적으로 주로 사용되는 값 테이블
 */


@Data
@Entity
@Table(name = "common_type")
public class CommonType {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "common_no")
    private Long commonNo; // 가계부번호
	
	@Column(name = "common_code")
	private String commonCode; //공통 코드
	
	@Column(name = "common_name")
	private String commonName; //공통 이름
	
	@Column(name = "del_at")
    private String delAt; // 삭제 여부
	
}
