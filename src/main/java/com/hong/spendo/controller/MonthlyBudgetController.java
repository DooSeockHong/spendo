package com.hong.spendo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.MonthlyBudgetDTO;
import com.hong.spendo.service.MonthlyBudgetService;

@RestController
@RequestMapping("/api/monthlyBudget")
public class MonthlyBudgetController {

	@Autowired
	public MonthlyBudgetService monthlyBudgetService;
	
	
	// 지출&수입 목표 등록
	@PostMapping("/monthlyBudgetAdd")
	public ResponseEntity monthlyBudgetAdd(@RequestBody MonthlyBudgetDTO monthlyBudgetDTO) {
		return monthlyBudgetService.monthlyBudgetAdd(monthlyBudgetDTO);
	}
	
	//지출&수입 목표 정보
	@GetMapping("/monthlyBudgeGet")
	public ResponseEntity monthlyBudgetList(@RequestParam(value = "year") String year, @RequestParam(value = "month")String month, @RequestParam(value = "spendoType")String spendoType) {
		return monthlyBudgetService.monthlyBudgetGet(year,month,spendoType);
	}
	
	//지출&수입 목표값과 총값
	@GetMapping("/monthlyTotalPriceGet")
	public ResponseEntity monthlyTotalPriceGet(@RequestParam(value = "year") String year, @RequestParam(value = "month")String month) {
		return monthlyBudgetService.monthlyTotalPriceGet(year, month);
	}
}
