package com.prueba.back.adapter.persistence.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.prueba.back.adapter.persistence.entity.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Long>{
	
	@Query(value = "select * from status_tbl where name_status = ?1",
            nativeQuery = true)
	Optional<StatusEntity> findByname(String name);

}
