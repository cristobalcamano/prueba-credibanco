package com.prueba.back.adapter.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.prueba.back.adapter.persistence.entity.BankCardEntity;

public interface BankCardRepository extends JpaRepository<BankCardEntity, Long>{
	
	@Query(value = "select * from bank_card where card_id = ?1",
            nativeQuery = true)
	Optional<BankCardEntity> findBynumber(String number);
	
	@Query(value = "select * from bank_card where card_id = ?1 and status_fk = ?2",
            nativeQuery = true)
	Optional<BankCardEntity> validateStatusCard(String number, Long status);

}
