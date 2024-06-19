package com.prueba.back.adapter.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prueba.back.adapter.persistence.entity.ProductoTypeEntity;

public interface ProductTypeRepository extends JpaRepository<ProductoTypeEntity, Long>{
	
}
