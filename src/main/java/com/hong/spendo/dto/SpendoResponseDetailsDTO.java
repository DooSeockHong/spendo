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
	// 가격
	private int spendoPrice; 
	// 날짜 시작
	private String cretDt;
	// 날짜 끝
	private String endDt;
}
