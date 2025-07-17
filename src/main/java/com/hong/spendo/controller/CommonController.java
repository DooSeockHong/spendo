package com.hong.spendo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.CommonCodeDTO;
import com.hong.spendo.service.CommonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/common")
public class CommonController {
	
	@Autowired
	public CommonService commonService;
	
	

	//공통코드 등록
	@PostMapping("/commonAdd")
	public ResponseEntity commonAdd(@Valid @RequestBody CommonCodeDTO commonCodeDTO) throws Exception {
		return commonService.commonAdd(commonCodeDTO);
	}
	
	//고통코드 지출,수입 불러오기
	@GetMapping("/commonExInCodeGet")
	public ResponseEntity commonExInCodeGet() throws Exception {
		return commonService.commonExInCodeGet();
	}
	
}
