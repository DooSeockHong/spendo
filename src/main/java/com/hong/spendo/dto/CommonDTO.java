package com.hong.spendo.dto;

import lombok.Data;

@Data
public class CommonDTO {
	
	// 날짜 시작
	private String startDt;
	// 날짜 끝
	private String endDt;
	// 지출&수입 타입
	private String spendoType;
}
