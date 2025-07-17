package com.hong.spendo.dto;


import lombok.Data;

@Data
public class SpendoEditDTO {

	//가계부 번호
	private Long spendoNo;
	// 제목
	private String spendoTitle; 
	// 내용
	private String spendoContent;
	// 가격
	private int spendoPrice; 

}
