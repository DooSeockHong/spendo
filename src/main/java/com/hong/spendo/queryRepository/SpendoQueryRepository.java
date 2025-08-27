package com.hong.spendo.queryRepository;



import com.hong.spendo.dto.ExpenditureMonthResponseDTO;
import com.hong.spendo.dto.ExpenditureResponseDTO;
import com.hong.spendo.dto.IncomeResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.hong.spendo.entity.QSpendo.spendo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

/*
 * queryDsl 쿼리
 */

@Repository
@RequiredArgsConstructor
public class SpendoQueryRepository {

	
	private final JPAQueryFactory queryFactory;
	
		
	
	//날짜기간에 지출관련  제목,가격 통계
	public List<ExpenditureResponseDTO> findBySpendoDateBetweenExpenditure(LocalDate startDate, LocalDate endDate, String spendoType) {
	    return queryFactory
	        .select(Projections.constructor(ExpenditureResponseDTO.class, spendo.spendoTitle, spendo.spendoPrice.sum().coalesce(0)))
	        .from(spendo)
	        .where(
	            spendo.spendoDate.between(startDate, endDate)
	                .and(spendo.delAt.eq("N"))
	                .and(spendo.spendoType.eq(spendoType))
	        ).groupBy(spendo.spendoTitle)
	        .fetch();
	}
	
	//월별에 대한 제목,총 가격
	public List<ExpenditureMonthResponseDTO> findByDateLike(String startDate) {
		return queryFactory
		       .select(Projections.constructor(ExpenditureMonthResponseDTO.class,spendo.spendoTitle,spendo.spendoPrice.sum().coalesce(0)))
		       .from(spendo)
		       .where(
		               spendo.spendoDate.stringValue().like("%" + startDate + "%"),
		               spendo.delAt.eq("N"),
		               spendo.spendoType.eq("EX01")
		       )
		       .groupBy(spendo.spendoTitle)
		       .fetch();
	}
	
	//기간별 대한 총 지출 가격
	public int findBySpendoDateAndExpenditurePrice(LocalDate startDate, String spendoType) {
		return queryFactory
			.select(spendo.spendoPrice.sum().coalesce(0))
			.from(spendo)
			.where(
		            spendo.spendoDate.stringValue().like("%" + startDate + "%"),
		                spendo.delAt.eq("N")
		                .and(spendo.spendoType.eq(spendoType))
		        )
			.fetchOne();
	}
	
	//날짜기간에 수입관련 제목,가격 통계
	public List<IncomeResponseDTO> findBySpendoDateBetweenIncome(LocalDate startDate, LocalDate endDate, String spendoType){
		return queryFactory
			.select(Projections.constructor(IncomeResponseDTO.class, spendo.spendoTitle, spendo.spendoPrice.sum().coalesce(0)))
			.from(spendo)
			.where(
		            spendo.spendoDate.between(startDate, endDate)
		                .and(spendo.delAt.eq("N"))
		                .and(spendo.spendoType.eq(spendoType))
		        )
			.groupBy(spendo.spendoTitle)
		    .fetch();
	}
	
	
	
	
	
}
