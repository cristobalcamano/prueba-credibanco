package com.prueba.back.application.port.out;

import com.prueba.back.domain.BankCardDomain;

public interface BuyTransactionOut {
	
	public String buyTransaction(BankCardDomain bankCardDomain, Long idTransaction);

}
