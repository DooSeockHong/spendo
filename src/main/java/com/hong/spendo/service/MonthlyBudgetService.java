package com.hong.spendo.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.MonthTotalResponseDTO;
import com.hong.spendo.dto.MonthlyBudgetDTO;
import com.hong.spendo.entity.MonthlyBudget;
import com.hong.spendo.enums.ResponseStatus;
import com.hong.spendo.queryRepository.SpendoQueryRepository;
import com.hong.spendo.repository.MonthlyBudgetRepository;

@Service
public class MonthlyBudgetService {

	@Autowired
	public MonthlyBudgetRepository monthlyBudgetRepository;
	
	@Autowired
	public SpendoQueryRepository spendoQueryRepository;
	
	// 지출&수입 월별 목표 금액 등록
	public ResponseEntity monthlyBudgetAdd(MonthlyBudgetDTO monthlyBudgetDTO) {
		
		MonthlyBudget monthlyBudgetValue = monthlyBudgetRepository.findByMonthlyBudgetYearAndMonthlyBudgetMonthAndSpendoTypeAndDelAt(monthlyBudgetDTO.getYear(), monthlyBudgetDTO.getMonth(),monthlyBudgetDTO.getSpendoType(),"N");
		
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
	public ResponseEntity monthlyBudgetGet(String year, String month, String spendoType) {
		
		MonthlyBudget monthlyBudget = monthlyBudgetRepository.findByMonthlyBudgetYearAndMonthlyBudgetMonthAndSpendoTypeAndDelAt(year, month, spendoType, "N");
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",monthlyBudget);
	}
	
	
	//지출&수입 년도월 목표값, 년도월 총 값
	public ResponseEntity monthlyTotalPriceGet(String year, String month) {
		
		String spendoDate = year+"-"+month;
		int monthValue = Integer.parseInt(month);
		String monthStr = String.valueOf(monthValue);
		
		//지출 목표 값
		MonthlyBudget monthlyExpenditureBudget = monthlyBudgetRepository.findByMonthlyBudgetYearAndMonthlyBudgetMonthAndSpendoTypeAndDelAt(year, monthStr, "EX01", "N");
		//수입 목표 값
		MonthlyBudget monthlyIncomeBudget = monthlyBudgetRepository.findByMonthlyBudgetYearAndMonthlyBudgetMonthAndSpendoTypeAndDelAt(year, monthStr, "IN01", "N");
		int expenditureTotalPrice = spendoQueryRepository.findByTotalPrice(spendoDate, "EX01");
		//수입 총값
		int incomeTotalPrice = spendoQueryRepository.findByTotalPrice(spendoDate,"IN01");
		
		MonthTotalResponseDTO monthTotalResponseDTO = new MonthTotalResponseDTO();
		monthTotalResponseDTO.setExpenditureBudgetPrice(monthlyExpenditureBudget.getMonthlyBudgetPrice());
		monthTotalResponseDTO.setIncomeBudgetPrice(monthlyIncomeBudget.getMonthlyBudgetPrice());
		monthTotalResponseDTO.setExpenditureTotalPrice(expenditureTotalPrice);
		monthTotalResponseDTO.setIncomeTotalPrice(incomeTotalPrice);
		
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",monthTotalResponseDTO);
	}
	
	
	
}
