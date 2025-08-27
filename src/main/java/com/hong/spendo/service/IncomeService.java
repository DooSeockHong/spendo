package com.hong.spendo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.CommonDTO;
import com.hong.spendo.dto.IncomeResponseDTO;
import com.hong.spendo.enums.ResponseStatus;
import com.hong.spendo.queryRepository.SpendoQueryRepository;

//수입통계 서비스

@Service
public class IncomeService {

	@Autowired
	public SpendoQueryRepository spendoQueryRepository;
	
	//수입 기간 통계
	public ResponseEntity incomeGet(CommonDTO commonDTO) throws Exception {
		LocalDate startDate = null;
		LocalDate endDate = null;
		DateTimeFormatter formatter;
		
		if(commonDTO == null) {
			return ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//문자를 날짜로변환
		startDate = LocalDate.parse(commonDTO.getStartDt(),formatter);
		endDate = LocalDate.parse(commonDTO.getEndDt(),formatter);
		
		List<IncomeResponseDTO> list = spendoQueryRepository.findBySpendoDateBetweenIncome(startDate, endDate,"IN01");
		
		for (int i=0; i < list.size(); i++) {
			list.get(i).setStartDt(commonDTO.getStartDt());
			list.get(i).setEndDt(commonDTO.getEndDt());
		}
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",list);
	}
	
	
}
