package com.hong.spendo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * * 월간 지출&수입 목표 가격 테이블
 */
@Data
@Entity
@Table(name = "monthly_budget")
public class MonthlyBudget {

    // 관리자 일련번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monthly_budget_no")
    private Long monthlyBudgetNo;

    // 년도
    @Column(name = "monthly_budget_year")
    private String monthlyBudgetYear;

    // 월
    @Column(name = "monthly_budget_month")
    private String monthlyBudgetMonth;

    // 목표가격
    @Column(name = "monthly_budget_price")
    private int monthlyBudgetPrice;

    // 지출,수입 구분 (common_type)
    @Column(name = "spendo_type")
    private String spendoType;

    // 삭제 여부
    @Column(name = "del_at")
    private String delAt = "N";

    // 생성날짜
    @Column(name = "cret_dt")
    private LocalDateTime cretDt = LocalDateTime.now();

    // 수정날짜
    @Column(name = "upd_dt")
    private LocalDateTime updDt;

}
