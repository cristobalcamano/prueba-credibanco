package com.prueba.back.adapter.persistence.entity;

import com.prueba.back.domain.BankCardDomain;

public class BankCardMapper {
	
	public static BankCardDomain entityToDomain(BankCardEntity entity) {
		BankCardDomain t = new BankCardDomain();
		t.setHolderName(entity.getCardholderName());
		t.setStatus(entity.getStatus().getName());
		t.setCardId(entity.getNumber());
		t.setNumber(entity.getNumber());
		t.setTypeProduct(ProductoTypeMapper.entityToDomain(entity.getProductType()));
		t.setExpiration(entity.getExpiration());
		t.setBalance(entity.getBalance());
		t.setId(entity.getId());
        return t;
    }
	

}
