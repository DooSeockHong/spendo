package com.hong.spendo.service;

import java.time.LocalDate;



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
		
		Spendo spendo = new Spendo();
		spendo.setUserNo(1l);
		spendo.setSpendoTitle(spendoDTO.getSpendoTitle());
		spendo.setSpendoContent(spendoDTO.getSpendoContent());
		spendo.setSpendoPrice(spendoDTO.getSpendoPrice());
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
		
		LocalDate startDate = null;
		LocalDate endDate = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		if (spendoListDTO.getCretDt() != null && spendoListDTO.getEndDt() != null) {
			//문자를 날짜로변환
			startDate = LocalDate.parse(spendoListDTO.getCretDt(),formatter);
			endDate = LocalDate.parse(spendoListDTO.getEndDt(),formatter);
		}
		
		//모든 필드가 "" 이거나 null이면 true
		boolean isAllFieldsEmpty = Stream.of(spendoListDTO.getSpendoTitle(), spendoListDTO.getCretDt(), spendoListDTO.getEndDt())
                .allMatch(s -> s == null || s.isBlank());
		// 조건이없는경우 다보여주기
		if(isAllFieldsEmpty) {
			spendoList = spendoRepository.findByDelAt("N");
		} else if(spendoListDTO.getSpendoTitle() != null && spendoListDTO.getCretDt() == null && spendoListDTO.getEndDt() == null) {
			//제목만있는조건
			spendoList = spendoRepository.findBySpendoTitleContainingAndDelAt(spendoListDTO.getSpendoTitle(),"N");
		} else if (spendoListDTO.getSpendoTitle() == null && spendoListDTO.getCretDt() != null && spendoListDTO.getEndDt() != null) {
			//날짜 검색
			spendoList = spendoRepository.findByCretDtBetweenAndDelAt(startDate,endDate,"N");
		} else if (spendoListDTO.getSpendoTitle() != null && spendoListDTO.getCretDt() != null && spendoListDTO.getEndDt() != null) {
			//제목,날짜 조건
			spendoList = spendoRepository.findBySpendoTitleContainingAndDelAtAndCretDtBetween(spendoListDTO.getSpendoTitle(),"N",startDate,endDate);
		} 
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",spendoList);
	}
	
	
	/**
	* 가계부 상세정보
	*/
	public ResponseEntity spendoDetails(Long spendoNo) throws Exception {
		
		if(spendoNo == 0 ) {
			return ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		ModelMapper modelMapper = new ModelMapper();
		Spendo spendo = spendoRepository.getBySpendoNoAndDelAt(spendoNo,"N");
		if(spendo == null) {
			return ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		SpendoResponseDetailsDTO spendoResponseDetailsDTO = modelMapper.map(spendo, SpendoResponseDetailsDTO.class);
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",spendoResponseDetailsDTO);
	}
	
	/**
	 * 가계부 수정
	 */
	public ResponseEntity spendoEdit(SpendoEditDTO spendoEditDTO) throws Exception {
		Spendo spendoEdit = new Spendo();
		Spendo spendo = spendoRepository.getBySpendoNoAndDelAt(spendoEditDTO.getSpendoNo(),"N");
		spendoEdit.setSpendoNo(spendo.getSpendoNo());
		spendoEdit.setUpdDt(LocalDate.now());
		spendoEdit.setUserNo(spendo.getUserNo());
		//제목수정
		if(spendoEditDTO.getSpendoTitle() != null && spendoEditDTO.getSpendoContent() == null && spendoEditDTO.getSpendoPrice() == 0) {
			spendoEdit.setSpendoTitle(spendoEditDTO.getSpendoTitle());
			spendoEdit.setSpendoContent(spendo.getSpendoContent());
			spendoEdit.setSpendoPrice(spendo.getSpendoPrice());
		} else if(spendoEditDTO.getSpendoTitle() == null && spendoEditDTO.getSpendoContent() != null && spendoEditDTO.getSpendoPrice() == 0) {
			//내용 수정
			spendoEdit.setSpendoContent(spendoEditDTO.getSpendoContent());
			spendoEdit.setSpendoTitle(spendo.getSpendoTitle());
			spendoEdit.setSpendoPrice(spendo.getSpendoPrice());
		} else if (spendoEditDTO.getSpendoTitle() == null && spendoEditDTO.getSpendoContent() == null && spendoEditDTO.getSpendoPrice() > 0) {
			//가격 수정
			spendoEdit.setSpendoPrice(spendoEditDTO.getSpendoPrice());
			spendoEdit.setSpendoTitle(spendo.getSpendoTitle());
			spendoEdit.setSpendoContent(spendo.getSpendoContent());
		} else if (spendoEditDTO.getSpendoTitle() != null && spendoEditDTO.getSpendoContent() != null && spendoEditDTO.getSpendoPrice() == 0) {
			//제목,내용 수정
			spendoEdit.setSpendoTitle(spendoEditDTO.getSpendoTitle());
			spendoEdit.setSpendoContent(spendoEditDTO.getSpendoContent());
			spendoEdit.setSpendoPrice(spendo.getSpendoPrice());
		} else if (spendoEditDTO.getSpendoTitle() != null && spendoEditDTO.getSpendoContent() == null && spendoEditDTO.getSpendoPrice() > 0) {
			//제목,가격 수정 
			spendoEdit.setSpendoTitle(spendoEditDTO.getSpendoTitle());
			spendoEdit.setSpendoContent(spendo.getSpendoContent());
			spendoEdit.setSpendoPrice(spendo.getSpendoPrice());
		} else if (spendoEditDTO.getSpendoTitle() != null && spendoEditDTO.getSpendoContent() == null && spendoEditDTO.getSpendoPrice() > 0) {
			//내용,가격 수정
			spendoEdit.setSpendoTitle(spendo.getSpendoTitle());
			spendoEdit.setSpendoContent(spendoEditDTO.getSpendoContent());
			spendoEdit.setSpendoPrice(spendo.getSpendoPrice());
		} else if (spendoEditDTO.getSpendoTitle() != null && spendoEditDTO.getSpendoContent() != null && spendoEditDTO.getSpendoPrice() > 0) {
			//제목,내용,가격 수정
			spendoEdit.setSpendoTitle(spendoEditDTO.getSpendoTitle());
			spendoEdit.setSpendoContent(spendoEditDTO.getSpendoContent());
			spendoEdit.setSpendoPrice(spendoEditDTO.getSpendoPrice());
		}
		Spendo spendoEditEntity = spendoRepository.save(spendoEdit);
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
		spendo.setUpdDt(LocalDate.now());
		spendoRepository.save(spendo);
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공");
		
	}
	
}
