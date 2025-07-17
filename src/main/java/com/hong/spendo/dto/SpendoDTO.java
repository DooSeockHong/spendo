package com.hong.spendo.dto;



import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * 
 * @DTO 명
 * 가계부내역 DTO
 *
 */

@Data
public class SpendoDTO {

	// 제목
	@NotBlank(message = "제목을 입력해주세요.")
    private String spendoTitle; 
	// 내용
	@NotBlank(message = "내용을 입력해주세요.")
    private String spendoContent;
	// 가격
	@Positive(message = "가격은 양수여야 합니다.")
    private int spendoPrice; 
	// 결제수단 구분
    private String spendoType; 
    // 은행코드
    private String spendoBank; 
	
}
