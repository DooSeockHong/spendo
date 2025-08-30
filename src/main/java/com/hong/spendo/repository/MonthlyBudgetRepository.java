package com.hong.spendo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hong.spendo.entity.MonthlyBudget;

@Repository
public interface MonthlyBudgetRepository extends JpaRepository<MonthlyBudget,Long> {

	public MonthlyBudget findByMonthlyBudgetYearAndMonthlyBudgetMonthAndSpendoTypeAndDelAt(String year, String month,String spendoType, String delAt);
	
	
}
