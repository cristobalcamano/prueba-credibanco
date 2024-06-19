package com.prueba.back.application.port.in;

import com.prueba.back.domain.TransactionBuyDomain;
import com.prueba.back.exeption.BusinessExeption;

public interface CheckTransactionIn {
	
	public TransactionBuyDomain checkTransaction(String transactionId) throws BusinessExeption;

}
