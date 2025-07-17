package com.hong.spendo.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	/*
	 *  엔티티값을 DTO 복사 
	 */
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
