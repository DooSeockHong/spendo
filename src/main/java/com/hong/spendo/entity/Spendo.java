package com.hong.spendo.entity;



import java.time.LocalDate;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 가계부내역 테이블
 */


@Data
@Entity
@Table(name = "spendo")
public class Spendo {

	// 가계부번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spendo_no")
    private Long spendoNo; 
    
    // 사용자
    @Column(name = "user_no")
    private Long userNo; 

    // 제목
    @Column(name = "spendo_title")
    private String spendoTitle; 

    // 내용
    @Column(name = "spendo_content")
    private String spendoContent; 

    // 가격
    @Column(name = "spendo_price")
    private int spendoPrice; 

    // 지출,수입 구분
    @Column(name = "spendo_type")
    private String spendoType; 

    // 체크카드,신용카드
    @Column(name = "spendo_code_type")
    private String spendoCodeType; 

    // 삭제 여부
    @Column(name = "del_at")
    private String delAt; 
    
    // 등록일
    @Column(name = "cret_dt")
    private LocalDate cretDt = LocalDate.now(); 

    // 수정일
    @Column(name = "upd_dt")
    private LocalDate updDt; 

    
    
 
    
}