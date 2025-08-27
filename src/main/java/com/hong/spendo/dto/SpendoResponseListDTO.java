package com.hong.spendo.dto;

import lombok.Data;

@Data
public class SpendoResponseListDTO {
	
	// 가계부번호
	private Long spendoNo;
	// 제목
	private String spendoTitle;
	// 지출,수입 구분
	private String spendoType;
	// 카드 구분 
	private String spendoCodeType;
	// 가격
	private int spendoPrice; 
	// 날짜
	private String spendoDate;
	
}
