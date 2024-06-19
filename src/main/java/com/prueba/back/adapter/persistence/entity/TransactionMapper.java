package com.prueba.back.adapter.persistence.entity;

import com.prueba.back.domain.TransactionBuyDomain;

public class TransactionMapper {
	
	public static TransactionBuyDomain entityToDomain(TransactionEntity entity) {
		TransactionBuyDomain t = new TransactionBuyDomain();
		t.setId(entity.getId());
		t.setStatusDomain(StatusMapper.entityToDomain(entity.getStatudId()));
		t.setBankCardDomain(BankCardMapper.entityToDomain(entity.getCard()));
		t.setSalesValue(entity.getSaleValue());
		t.setTransactionId(entity.getTransactionId());
		t.setCreatedDate(entity.getCreationDate());
		return t;
	}

}
