package com.hong.spendo.dto;

import lombok.Data;

@Data
public class MonthlyBudgetDTO {

	//연도
	private String year; 
	//월
	private String month;
	//가격 
	private int price;
	//지출&수입 타입
	private String spendoType;
	
}
