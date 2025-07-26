package com.hong.spendo.dto;

import lombok.Data;

@Data
public class SpendoResponseDetailsDTO {

	// 가계부번호
	private Long spendoNo;
	// 제목
	private String spendoTitle;
	//내용
	private String spendoContent;
	// 지출,수입 구분
	private String spendoType;
	// 카드 구분 
	private String spendoCodeType;
	// 가격
	private int spendoPrice; 
	// 날짜 
	private String cretDt;
	
}
