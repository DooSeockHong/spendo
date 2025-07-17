package com.hong.spendo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hong.spendo.entity.CommonType;

public interface CommonTypeRepository extends JpaRepository<CommonType,Long> {

	public List<CommonType> findByCommonCodeOrCommonCodeAndDelAt(String commCode1 , String commCode2,String delAt);
	
}
