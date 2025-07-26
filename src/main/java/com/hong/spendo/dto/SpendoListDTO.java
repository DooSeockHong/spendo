package com.hong.spendo.dto;

import lombok.Data;

@Data
public class SpendoListDTO {
	
	// 제목
	private String spendoTitle;
	// 지출,수입 구분
	private String spendoType;
	// 카드 구분 
	private String spendoCodeType;
	// 날짜 시작
	private String cretDt;
	// 날짜 끝
	private String endDt;
	
	
	
}

