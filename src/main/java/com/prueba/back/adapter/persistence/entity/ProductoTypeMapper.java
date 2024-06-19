package com.prueba.back.adapter.persistence.entity;

import com.prueba.back.domain.ProductTypeDomain;

public class ProductoTypeMapper {
	
	public static ProductTypeDomain entityToDomain(ProductoTypeEntity entity) {
		ProductTypeDomain t = new ProductTypeDomain();
		t.setId(entity.getId());
		t.setDescription(entity.getDescription());
		t.setName(entity.getName());
		return t;
	}

}
