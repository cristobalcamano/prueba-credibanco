package com.prueba.back.application.port.out;

import com.prueba.back.domain.TransactionBuyDomain;

public interface AnulationTransactionOut {
	
	public void anulationTransaction(TransactionBuyDomain transactionBuyDomain);

}
