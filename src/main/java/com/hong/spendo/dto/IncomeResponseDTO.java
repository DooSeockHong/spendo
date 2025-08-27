package com.hong.spendo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeResponseDTO {

	//가격
    private int incomePrice;
    
    // 날짜 시작
    private String startDt;
    
    // 날짜 끝
    private String endDt;
    
    //제목
    private String spendoTitle;
    
    public IncomeResponseDTO (String spendoTitle,int incomePrice) {
    	this.spendoTitle = spendoTitle;
    	this.incomePrice = incomePrice;
    }
    
}
