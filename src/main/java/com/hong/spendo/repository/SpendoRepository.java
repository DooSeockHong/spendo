package com.hong.spendo.repository;



import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hong.spendo.dto.ExpenditureResponseDTO;
import com.hong.spendo.dto.SpendoResponseDetailsDTO;
import com.hong.spendo.dto.SpendoResponseListDTO;
import com.hong.spendo.entity.Spendo;

public interface SpendoRepository extends JpaRepository<Spendo, Long> {
	
	public List<SpendoResponseListDTO> findByDelAt(String delAt);
	public List<SpendoResponseListDTO> findBySpendoTitleContainingAndDelAt(String spendoTitle,String delAt);
	public List<SpendoResponseListDTO> findByCretDtBetweenAndDelAt(LocalDate startDate, LocalDate endDate,String delAt);
	public List<SpendoResponseListDTO> findBySpendoTitleContainingAndDelAtAndCretDtBetween(String spendoTitle,String delAt,LocalDate startDate, LocalDate endDate);
	public Spendo getBySpendoNoAndDelAt(Long spendoNo,String delAt);
	
}
