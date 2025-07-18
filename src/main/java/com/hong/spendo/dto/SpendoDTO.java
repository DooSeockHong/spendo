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
	// 지출, 수입 구분 코드
	@NotBlank(message = "지출,수입구분 코드를 입력해주세요.")
    private String spendoType; 
    // 체크카드,신용카드,기타 구분
	@NotBlank(message = "체크카드,신용카드,기타 구분 코드를 입력해주세요.")
    private String spendoCodeType; 
	
}
