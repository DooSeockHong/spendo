package com.hong.spendo.service;

import java.time.LocalDate;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.SpendoDetailsDTO;
import com.hong.spendo.dto.SpendoEditDTO;
import com.hong.spendo.dto.SpendoDTO;
import com.hong.spendo.dto.SpendoListDTO;
import com.hong.spendo.dto.SpendoResponseDetailsDTO;
import com.hong.spendo.dto.SpendoResponseListDTO;
import com.hong.spendo.entity.QSpendo;
import com.hong.spendo.entity.Spendo;
import com.hong.spendo.enums.ResponseStatus;
import com.hong.spendo.repository.SpendoRepository;


import org.modelmapper.ModelMapper;


@Service
public class SpendoService {
	
	@Autowired
	public SpendoRepository spendoRepository;
	

    /**
	* 가계부 등록
	*/
	public ResponseEntity spendoAdd(SpendoDTO spendoDTO) throws Exception {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Spendo spendo = new Spendo();
		spendo.setUserNo(1l);
		spendo.setSpendoDate(LocalDate.parse(spendoDTO.getSpendoDate(),formatter));
		spendo.setSpendoTitle(spendoDTO.getSpendoTitle());
		spendo.setSpendoContent(spendoDTO.getSpendoContent());
		spendo.setSpendoPrice(spendoDTO.getSpendoPrice());
		spendo.setSpendoType(spendoDTO.getSpendoType());
		spendo.setSpendoCodeType(spendoDTO.getSpendoCodeType());
		
		Spendo spendoIns = spendoRepository.save(spendo);
		//등록 데이터 없을시 실패 
		if(spendoIns == null) {
			return ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공");
	}
	
	/**
	* 가계부목록
	*/
	public ResponseEntity spendoList(SpendoListDTO spendoListDTO ) throws Exception {
		List<SpendoResponseListDTO> spendoList = new ArrayList<>();
		List<Spendo> spendoArray = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		//가계부 해당날짜 정보 다가져오기
		if(spendoListDTO.getSpendoDate() != null) {
			spendoArray = spendoRepository.findBySpendoDateAndDelAt(LocalDate.parse(spendoListDTO.getSpendoDate(),formatter),"N");
		}
		
		
		//날짜만 있는 조건값
		if(spendoListDTO.getStartDt() != null && spendoListDTO.getEndDt() != null && spendoListDTO.getSpendoDate() == null && spendoListDTO.getSpendoTitle().equals("") && spendoListDTO.getSpendoType().equals("") && spendoListDTO.getSpendoCodeType().equals("")) {	
			 spendoArray = spendoRepository.findBySpendoDateBetweenAndDelAt(LocalDate.parse(spendoListDTO.getStartDt(),formatter),LocalDate.parse(spendoListDTO.getEndDt(),formatter),"N");
		} else if(spendoListDTO.getStartDt() != null && spendoListDTO.getEndDt() != null && spendoListDTO.getSpendoDate() == null && !(spendoListDTO.getSpendoTitle().equals("")) && !(spendoListDTO.getSpendoType().equals("")) && !(spendoListDTO.getSpendoCodeType().equals(""))) {
			//날짜 및 조건값 있는 값 
			spendoArray = spendoRepository.findBySpendoDateBetweenAndSpendoTitleContainingAndSpendoTypeAndSpendoCodeTypeAndDelAt(LocalDate.parse(spendoListDTO.getStartDt(),formatter),LocalDate.parse(spendoListDTO.getEndDt(),formatter),spendoListDTO.getSpendoTitle(),spendoListDTO.getSpendoType(),spendoListDTO.getSpendoCodeType(),"N");	
		} else if(spendoListDTO.getStartDt() != null && spendoListDTO.getEndDt() != null && spendoListDTO.getSpendoDate() == null && !(spendoListDTO.getSpendoTitle().equals("")) && spendoListDTO.getSpendoType().equals("") && spendoListDTO.getSpendoCodeType().equals("")) {
			//날짜,제목 조건값 있는 값
			spendoArray = spendoRepository.findBySpendoDateBetweenAndSpendoTitleContainingAndDelAt(LocalDate.parse(spendoListDTO.getStartDt(),formatter),LocalDate.parse(spendoListDTO.getEndDt(),formatter),spendoListDTO.getSpendoTitle(),"N");
		} else if(spendoListDTO.getStartDt() != null && spendoListDTO.getEndDt() != null && spendoListDTO.getSpendoDate() == null && !(spendoListDTO.getSpendoTitle().equals("")) && !(spendoListDTO.getSpendoType().equals("")) && spendoListDTO.getSpendoCodeType().equals("")) {
			//날짜,제목,지출&수입 조건값 있는 값
			spendoArray = spendoRepository.findBySpendoDateBetweenAndSpendoTitleContainingAndSpendoTypeAndDelAt(LocalDate.parse(spendoListDTO.getStartDt(),formatter),LocalDate.parse(spendoListDTO.getEndDt(),formatter),spendoListDTO.getSpendoTitle(),spendoListDTO.getSpendoType(),"N");
		} else if(spendoListDTO.getStartDt() != null && spendoListDTO.getEndDt() != null && spendoListDTO.getSpendoDate() == null && !(spendoListDTO.getSpendoTitle().equals("")) && spendoListDTO.getSpendoType().equals("") && !(spendoListDTO.getSpendoCodeType().equals(""))) {
			//날짜,제목,카드종류 조건값 있는 값
			spendoArray = spendoRepository.findBySpendoDateBetweenAndSpendoTitleContainingAndSpendoCodeTypeAndDelAt(LocalDate.parse(spendoListDTO.getStartDt(),formatter),LocalDate.parse(spendoListDTO.getEndDt(),formatter),spendoListDTO.getSpendoTitle(),spendoListDTO.getSpendoCodeType(),"N");
		} else if(spendoListDTO.getStartDt() != null && spendoListDTO.getEndDt() != null && spendoListDTO.getSpendoDate() == null && spendoListDTO.getSpendoTitle().equals("") && !(spendoListDTO.getSpendoType().equals("")) && spendoListDTO.getSpendoCodeType().equals("")) {
			//날짜,지출&수입 조건값 있는 값
			spendoArray = spendoRepository.findBySpendoDateBetweenAndSpendoTypeAndDelAt(LocalDate.parse(spendoListDTO.getStartDt(),formatter),LocalDate.parse(spendoListDTO.getEndDt(),formatter),spendoListDTO.getSpendoType(),"N");
		} else if(spendoListDTO.getStartDt() != null && spendoListDTO.getEndDt() != null && spendoListDTO.getSpendoDate() == null && spendoListDTO.getSpendoTitle().equals("") && spendoListDTO.getSpendoType().equals("") && !(spendoListDTO.getSpendoCodeType().equals(""))) {
			//날짜,카드 조건값 있는 값
			spendoArray = spendoRepository.findBySpendoDateBetweenAndSpendoCodeTypeAndDelAt(LocalDate.parse(spendoListDTO.getStartDt(),formatter),LocalDate.parse(spendoListDTO.getEndDt(),formatter),spendoListDTO.getSpendoCodeType(),"N");		
		}
		
		
		
		
		for(int i = 0; i < spendoArray.size(); i++) {
			if(spendoArray.get(i).getSpendoType().equals("EX01")) {
				spendoArray.get(i).setSpendoType("지출");
			} else if (spendoArray.get(i).getSpendoType().equals("IN01")) {
				spendoArray.get(i).setSpendoType("수입");
			}
		
			if(spendoArray.get(i).getSpendoCodeType().equals("CC01")) {
				spendoArray.get(i).setSpendoCodeType("체크카드");
			} else if(spendoArray.get(i).getSpendoCodeType().equals("CR01")) {
				spendoArray.get(i).setSpendoCodeType("신용카드");
			}
		}
		
		

		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",spendoArray);
	}
	
	
	/**
	* 가계부 상세정보
	*/
	public ResponseEntity spendoDetails(Long spendoNo) throws Exception {
		
		if(spendoNo == 0 ) {
			return ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		System.out.println("아라라아라라아아");
		ModelMapper modelMapper = new ModelMapper();
		Spendo spendo = spendoRepository.getBySpendoNoAndDelAt(spendoNo,"N");
		if(spendo == null) {
			return ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		SpendoResponseDetailsDTO spendoResponseDetailsDTO = modelMapper.map(spendo, SpendoResponseDetailsDTO.class);
		
		switch (spendoResponseDetailsDTO.getSpendoType()) {
			case "EX01" : 
				spendoResponseDetailsDTO.setSpendoType("지출");
				break;
			case "IN01" :
				spendoResponseDetailsDTO.setSpendoType("수입");
				break;
		}
		
		switch (spendoResponseDetailsDTO.getSpendoCodeType()) {
			case "CC01" :
				spendoResponseDetailsDTO.setSpendoCodeType("체크카드");
				break;
			case "CR01" :
				spendoResponseDetailsDTO.setSpendoCodeType("신용카드");
				break;
		}
		
		
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",spendoResponseDetailsDTO);
	}
	
	/**
	 * 가계부 수정
	 */
	public ResponseEntity spendoEdit(SpendoEditDTO spendoEditDTO) throws Exception {

		Spendo spendo = spendoRepository.getBySpendoNoAndDelAt(spendoEditDTO.getSpendoNo(),"N");
		spendo.setSpendoNo(spendo.getSpendoNo());
		spendo.setUpdDt(LocalDateTime.now());
		spendo.setUserNo(spendo.getUserNo());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		
		//날짜
		if(spendoEditDTO.getSpendoDate() != null) {
			spendo.setSpendoDate(LocalDate.parse(spendoEditDTO.getSpendoDate(),formatter));
		}
		//제목
		if(!(spendoEditDTO.getSpendoTitle().equals(""))) {
			spendo.setSpendoTitle(spendoEditDTO.getSpendoTitle());
		}
		//내용
		if(!(spendoEditDTO.getSpendoContent().equals(""))) {
			spendo.setSpendoContent(spendoEditDTO.getSpendoContent());
		}
		//가격
		if(spendoEditDTO.getSpendoPrice() > 0) {
			spendo.setSpendoPrice(spendoEditDTO.getSpendoPrice());
		}
		
		//수입&지출 타입
		if(!(spendoEditDTO.getSpendoType().equals(""))) {
			spendo.setSpendoType(spendoEditDTO.getSpendoType());
		}
		//카드 타입
		if(!(spendoEditDTO.getSpendoCodeType().equals(""))) {
			spendo.setSpendoCodeType(spendoEditDTO.getSpendoCodeType());
		}
		
		
		Spendo spendoEditEntity = spendoRepository.save(spendo);
		ModelMapper modelMapper = new ModelMapper();
		//엔디티 값을 DTO로 복사
		SpendoResponseDetailsDTO spendoResponseDetailsDTO = modelMapper.map(spendoEditEntity, SpendoResponseDetailsDTO.class);
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",spendoResponseDetailsDTO);
	}
	
	/**
	 * 가계부 삭제
	 */
	
	public ResponseEntity spendoDel(Long spendoNo) throws Exception {
		
		if(spendoNo == 0 ) {
			return ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		
		Spendo spendo = spendoRepository.getBySpendoNoAndDelAt(spendoNo,"N");
		//조회 데이터 없을시 실패
		if(spendo == null) {
			return ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		
		spendo.setDelAt("Y");
		spendo.setUpdDt(LocalDateTime.now());
		spendoRepository.save(spendo);
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공");
		
	}
	
}
