package com.prueba.back.adapter.persistence.entity;

import com.prueba.back.domain.CoinDomain;

public class CoinMapper {
	
	public static CoinDomain entityToDomain(CoinEntity entity) {
		CoinDomain m= new CoinDomain();
		m.setId(entity.getId());
		m.setDescription(entity.getDescription());
		m.setName(entity.getName());
		return m;
	}

}
