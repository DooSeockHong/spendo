package com.hong.spendo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.SpendoDetailsDTO;
import com.hong.spendo.dto.SpendoEditDTO;
import com.hong.spendo.dto.SpendoDTO;
import com.hong.spendo.dto.SpendoListDTO;
import com.hong.spendo.service.SpendoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/spendo")
public class SpendoController {

	@Autowired
	public SpendoService spendoService;
	
	//가계부 등록
	@PostMapping("/spendoAdd")
	public ResponseEntity spendoAdd(@Valid @RequestBody SpendoDTO spendoDTO) throws Exception {
		return spendoService.spendoAdd(spendoDTO);
	}
	
	
	//가계부 목록
	@GetMapping("/spendoList")
	public ResponseEntity spendoList(SpendoListDTO spendoListDTO) throws Exception {
		return spendoService.spendoList(spendoListDTO);
	}
	
	//가계부 상세정보
	@GetMapping("/spendoDetails")
	public ResponseEntity spedoDetails(@RequestParam(value = "spendoNo") Long spendoNo) throws Exception {
		return spendoService.spendoDetails(spendoNo);
	}
	
	//가계부 수정
	@PostMapping("/spendoEdit")
	public ResponseEntity spendoEdit(@RequestBody SpendoEditDTO spendoEditDTO) throws Exception {
		return spendoService.spendoEdit(spendoEditDTO);
	}
	
	//가계부 삭제 
	@PostMapping("/spendoDel")
	public ResponseEntity spendoDel(@RequestParam(value = "spendoNo") Long spendoNo) throws Exception {
		return spendoService.spendoDel(spendoNo);
	}
	
}
