package com.hong.spendo.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.MonthlyBudgetDTO;
import com.hong.spendo.entity.MonthlyBudget;
import com.hong.spendo.enums.ResponseStatus;
import com.hong.spendo.repository.MonthlyBudgetRepository;

@Service
public class MonthlyBudgetService {

	@Autowired
	public MonthlyBudgetRepository monthlyBudgetRepository;
	
	// 지출&수입 월별 목표 금액 등록
	
	public ResponseEntity monthlyBudgetAdd(MonthlyBudgetDTO monthlyBudgetDTO) {
		
		MonthlyBudget monthlyBudgetValue = monthlyBudgetRepository.findByMonthlyBudgetYearAndMonthlyBudgetMonthAndDelAt(monthlyBudgetDTO.getYear(), monthlyBudgetDTO.getMonth(), "N");
		
		if(Objects.isNull(monthlyBudgetValue)) {
			//값이 없을경우 수정
			MonthlyBudget monthlyBudget = new MonthlyBudget();
			monthlyBudget.setMonthlyBudgetYear(monthlyBudgetDTO.getYear());
			monthlyBudget.setMonthlyBudgetMonth(monthlyBudgetDTO.getMonth());
			monthlyBudget.setMonthlyBudgetPrice(monthlyBudgetDTO.getPrice());
			monthlyBudget.setSpendoType(monthlyBudgetDTO.getSpendoType());
			monthlyBudgetRepository.save(monthlyBudget);
		} else {
			//값이 있을경우 수정
			monthlyBudgetValue.setMonthlyBudgetPrice(monthlyBudgetDTO.getPrice());
			monthlyBudgetRepository.save(monthlyBudgetValue);
		}	
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공");
	}
	
	// 지출&수입 월별 목표 목록
	
	public ResponseEntity monthlyBudgetGet(String year, String month) {
		
		MonthlyBudget monthlyBudget = monthlyBudgetRepository.findByMonthlyBudgetYearAndMonthlyBudgetMonthAndDelAt(year, month, "N");
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",monthlyBudget);
	}
	
	
	
}
