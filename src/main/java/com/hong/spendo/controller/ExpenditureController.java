package com.hong.spendo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.CommonDTO;
import com.hong.spendo.service.ExpenditureService;

@RestController
@RequestMapping("/api/expenditure")
public class ExpenditureController {

	@Autowired
	public ExpenditureService expenditureService;
	
	//지출 통계
	@GetMapping("/expenditureGet")
	public ResponseEntity expenditureGet(@RequestParam(value = "startDt") String startDt, @RequestParam(value = "endDt") String endDt ) throws Exception {
		CommonDTO commonDTO = new CommonDTO();
		commonDTO.setStartDt(startDt);
		commonDTO.setEndDt(endDt);
		return expenditureService.expenditureGet(commonDTO);
	}
	
	//지출 총 통계가격
	@GetMapping("/expenditurePriceGet")
	public ResponseEntity expenditurePriceGet(@RequestParam(value = "spendoDate") String spendoDate ) throws Exception {
		return expenditureService.expenditurePriceGet(spendoDate);
	}
	
	//월별 통계
	@GetMapping("/monthExpenditureGet")
	public ResponseEntity monthExpenditureGet(@RequestParam(value = "startDt") String startDt) throws Exception {
		return expenditureService.monthExpenditureGet(startDt);
	}
}
