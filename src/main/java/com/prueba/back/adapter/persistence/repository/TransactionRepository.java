package com.prueba.back.adapter.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.prueba.back.adapter.persistence.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long>{
	
	@Query(value = "select * from transaction where transaction_id = ?1",
            nativeQuery = true)
	Optional<TransactionEntity> findByTransactionId(String transaction);
	
	@Query(value = "select * from transaction where transaction_id = ?1 and statud_id = ?2",
            nativeQuery = true)
	Optional<TransactionEntity> validateStatusTransaction(String transaction, Long status);

}
