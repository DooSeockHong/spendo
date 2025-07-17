package com.hong.spendo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.CommonCodeDTO;
import com.hong.spendo.entity.CommonType;
import com.hong.spendo.enums.ResponseStatus;
import com.hong.spendo.repository.CommonTypeRepository;


@Service
public class CommonService {

	@Autowired
	public CommonTypeRepository commonTypeRepository;
	
	
	//공통코드 등록
	public ResponseEntity commonAdd(CommonCodeDTO commonCodeDTO) throws Exception {
		
		if(commonCodeDTO == null) {
			ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		
		CommonType commonType = new CommonType();
		commonType.setCommonCode(commonCodeDTO.getCommonCode());
		commonType.setCommonName(commonCodeDTO.getCommonName());
		commonType.setDelAt("N");
		commonTypeRepository.save(commonType);
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공");
	}
	
	//공통코드 지출,수입 불러오기
	public ResponseEntity commonExInCodeGet() throws Exception {
		
		
		List<CommonType> list = commonTypeRepository.findByCommonCodeOrCommonCodeAndDelAt("EX01", "IN01", "N");
		
		if(list.isEmpty()) {
			ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공",list);
	}
	
	
}
