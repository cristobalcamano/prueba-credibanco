package com.prueba.back.application.port.out;

import com.prueba.back.domain.TransactionBuyDomain;
import com.prueba.back.exeption.BusinessExeption;

public interface CheckTransactionOut {

	public TransactionBuyDomain checkTransaction(String transactionId) throws BusinessExeption;

}
