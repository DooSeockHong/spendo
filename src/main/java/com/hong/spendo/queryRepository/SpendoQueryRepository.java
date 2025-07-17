package com.hong.spendo.queryRepository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.hong.spendo.entity.QSpendo.spendo;

import java.time.LocalDate;

/*
 * queryDsl 쿼리
 */

@Repository
@RequiredArgsConstructor
public class SpendoQueryRepository {

	
	private final JPAQueryFactory queryFactory;
	
	
	//날짜에 대한 총 가격
	public int findByCretDtBetween(LocalDate startDate, LocalDate endDate) {
		 
		return queryFactory
	           .select(spendo.spendoPrice.sum().coalesce(0))
	           .from(spendo)
	           .where(spendo.cretDt.between(startDate, endDate).and(spendo.delAt.eq("N")))
	           .fetchOne();
	}
	
	//월별에 대한 총 가격
	public int findByDateLike(String startDt) {
		return queryFactory
		       .select(spendo.spendoPrice.sum().coalesce(0))
		       .from(spendo)
		       .where(
		               spendo.cretDt.stringValue().like("%" + startDt + "%"),
		               spendo.delAt.eq("N")
		       )
		       .fetchOne();
	}
}
