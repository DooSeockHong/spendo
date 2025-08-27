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
	
	public List<Spendo> findBySpendoDateAndDelAt(LocalDate spendoDate,String delAt);
	public List<Spendo> findBySpendoDateBetweenAndDelAt(LocalDate startDate, LocalDate endDate,String delAt);
	public List<Spendo> findByDelAt(String delAt);
	public List<Spendo> findBySpendoDateBetweenAndSpendoTitleContainingAndDelAt(LocalDate startDate, LocalDate endDate,String spendoTitle,String delAt);
	public List<Spendo> findBySpendoDateBetweenAndSpendoTitleContainingAndSpendoTypeAndSpendoCodeTypeAndDelAt(LocalDate startDate, LocalDate endDate,String spendoTitle,String spendoType,String spendoCodeType,String delAt);
	public List<Spendo> findBySpendoDateBetweenAndSpendoTitleContainingAndSpendoTypeAndDelAt(LocalDate startDate, LocalDate endDate,String spendoTitle,String spendoType,String delAt);
	public List<Spendo> findBySpendoDateBetweenAndSpendoTitleContainingAndSpendoCodeTypeAndDelAt(LocalDate startDate, LocalDate endDate,String spendoTitle,String SpendoCodeType,String delAt);
	public List<Spendo> findBySpendoDateBetweenAndSpendoTypeAndDelAt(LocalDate startDate, LocalDate endDate,String spendoType,String delAt);
	public List<Spendo> findBySpendoDateBetweenAndSpendoCodeTypeAndDelAt(LocalDate startDate, LocalDate endDate,String spendoCodeType,String delAt);
	public Spendo getBySpendoNoAndDelAt(Long spendoNo,String delAt);
	
}
