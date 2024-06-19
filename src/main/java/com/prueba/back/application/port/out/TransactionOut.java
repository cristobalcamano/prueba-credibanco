package com.prueba.back.application.port.out;

import com.prueba.back.domain.TransactionBuyDomain;
import com.prueba.back.exeption.BusinessExeption;

public interface TransactionOut {

	public TransactionBuyDomain validateStatusTransaction(String transactionId, Long status) throws BusinessExeption;
	
}
