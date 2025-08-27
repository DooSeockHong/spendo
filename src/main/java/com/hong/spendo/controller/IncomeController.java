package com.hong.spendo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.CommonDTO;
import com.hong.spendo.service.IncomeService;

@RestController
@RequestMapping("/api/income")
public class IncomeController {

	@Autowired
	public IncomeService incomeService;
	
	
	//기간 수입 통계
	@GetMapping("/incomeGet")
	public ResponseEntity incomeGet(@RequestParam(value = "startDt") String startDt, @RequestParam(value = "endDt") String endDt ) throws Exception {
		CommonDTO commonDTO = new CommonDTO();
		commonDTO.setStartDt(startDt);
		commonDTO.setEndDt(endDt);
		return incomeService.incomeGet(commonDTO);
	}
	
	
}
