package com.hong.spendo.dto;

import lombok.Data;

@Data
public class SpendoResponseListDTO {
	
	// 가계부번호
	private Long spendoNo;
	// 제목
	private String spendoTitle;
	// 가격
	private int spendoPrice; 
	// 날짜 시작
	private String cretDt;
	
}
