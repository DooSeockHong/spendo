package com.hong.spendo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hong.spendo.common.ResponseEntity;
import com.hong.spendo.dto.UserRequestDTO;
import com.hong.spendo.entity.User;
import com.hong.spendo.enums.ResponseStatus;
import com.hong.spendo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	
	@Autowired
	public UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	
	/**
	 * 사용자 가입
	 */
	
	public ResponseEntity userRegister(UserRequestDTO userRequestDTO) throws Exception {
		
		String encodedPw = passwordEncoder.encode(userRequestDTO.getUserPw()); // BCrypt 비밀번호암호화
		
		User user = new User();
		user.setUserEmail(userRequestDTO.getUserEmail());
		user.setUserPw(encodedPw);
		user.setUserName(userRequestDTO.getUserName());
		user.setDelAt("N");
		User userInfo = userRepository.save(user);
		if(userInfo == null ) {
			return ResponseEntity.of(ResponseStatus.FAIL,"실패");
		}
		
		
		return ResponseEntity.of(ResponseStatus.SUCCESS,"성공");
	}
	
}
