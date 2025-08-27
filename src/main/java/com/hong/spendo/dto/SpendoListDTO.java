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
	private String startDt;
	// 날짜 끝
	private String endDt;
	//날짜
	private String spendoDate;
	
	
	
}

