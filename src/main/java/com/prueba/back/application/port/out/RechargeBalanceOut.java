package com.prueba.back.application.port.out;

import com.prueba.back.domain.BankCardDomain;

public interface RechargeBalanceOut {
	
	public void rechargeBalance(BankCardDomain bankCardDomain);

}
