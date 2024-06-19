package com.prueba.back.application.port.in;

import com.prueba.back.domain.TransactionBuyDomain;
import com.prueba.back.exeption.BusinessExeption;

public interface AnulationTransactionIn {
	
	public Boolean anulationTransaction(TransactionBuyDomain transactionBuyDomain) throws BusinessExeption;

}
