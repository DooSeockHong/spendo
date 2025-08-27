package com.hong.spendo.service;

import java.time.LocalDate;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.hong.spendo.dto.ExpenditureResponseDTO;
import com.hong.spendo.dto.ExpenditureMonthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.CommonDTO;
import com.hong.spendo.dto.SpendoResponseListDTO;
import com.hong.spendo.enums.ResponseStatus;
import com.hong.spendo.queryRepository.SpendoQueryRepository;
import com.hong.spendo.repository.SpendoRepository;

@Service
public class ExpenditureService {

	
	@Autowired
	public SpendoRepository spendoRepository;
	
	@Autowired
	public SpendoQueryRepository spendoQueryRepository;
	
	
	/**
	* 기간별 지출 통계 
	*/
	public ResponseEntity expenditureGet(CommonDTO commonDTO) throws Exception {
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
		
		List<ExpenditureResponseDTO> list = spendoQueryRepository.findBySpendoDateBetweenExpenditure(startDate, endDate, "EX01");
		
		for (int i=0; i < list.size(); i++) {
			list.get(i).setStartDt(commonDTO.getStartDt());
			list.get(i).setEndDt(commonDTO.getEndDt());
		}
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",list);
	}
	
	
	/**
	* 기간별 지출 가격 통계 
	*/
	public ResponseEntity expenditurePriceGet(String spendoDate) throws Exception {
		LocalDate startDate = null;
		LocalDate endDate = null;
		DateTimeFormatter formatter;
		ExpenditureResponseDTO expenditureResponseDTO = new ExpenditureResponseDTO();
		
		if(spendoDate == null) {
			return ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		
		
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//문자를 날짜로변환
		startDate = LocalDate.parse(spendoDate,formatter);
		endDate = LocalDate.parse(spendoDate,formatter);
		
		int expenditurePrice = spendoQueryRepository.findBySpendoDateAndExpenditurePrice(startDate,"EX01");
		expenditureResponseDTO.setStartDt(startDate.toString());
		expenditureResponseDTO.setEndDt(endDate.toString());
		expenditureResponseDTO.setExpenditurePrice(expenditurePrice);
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",expenditureResponseDTO);
	}
	
	
	/*
	 * 월별 지출 통계
	 */
	public ResponseEntity monthExpenditureGet(String startDt) throws Exception {
		
		if(startDt == null || startDt.equals("")) {
			return ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		
		LocalDate date = LocalDate.parse(startDt, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String monthAndYear = date.format(DateTimeFormatter.ofPattern("yyyy-MM"));
		
		List<ExpenditureMonthResponseDTO> list = spendoQueryRepository.findByDateLike(monthAndYear);
		System.out.println("list " + list);
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",list);
	}
	
	
	
	
}
