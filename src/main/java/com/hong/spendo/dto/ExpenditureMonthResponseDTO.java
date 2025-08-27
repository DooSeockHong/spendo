package com.hong.spendo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenditureMonthResponseDTO {

	//가격
	private int expenditureMonthSumPrice;
		
	// 날짜
	private String startDt;
	
	//제목
    private String spendoTitle;
    
    public ExpenditureMonthResponseDTO(String spendoTitle,int expenditureMonthSumPrice) {
    	this.spendoTitle = spendoTitle;
    	this.expenditureMonthSumPrice = expenditureMonthSumPrice;
    }
    
}
