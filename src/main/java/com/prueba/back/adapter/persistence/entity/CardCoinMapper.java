package com.prueba.back.adapter.persistence.entity;

import com.prueba.back.domain.CoinCardDomain;

public class CardCoinMapper {
	
	public static  CoinCardDomain entityToDomain(CardCoinEntity entity) {
		CoinCardDomain m = new CoinCardDomain();
		m.setCoin(CoinMapper.entityToDomain(entity.getCoin()));
		return m;
	}

}
