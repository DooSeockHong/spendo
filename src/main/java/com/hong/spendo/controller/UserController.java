package com.hong.spendo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.UserRequestDTO;
import com.hong.spendo.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	public UserService userService;
	
	@PostMapping("/userRegister")
	public ResponseEntity userRegister(@Valid @RequestBody UserRequestDTO userRequestDTO) throws Exception {
		return userService.userRegister(userRequestDTO);
		
	}
	
	
	
}
