package com.prueba.back.application.port.in;

import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.exeption.BusinessExeption;

public interface RechargeBalanceIn {
	
	public Boolean rechargeBalance(BankCardDomain bankCardDomain) throws BusinessExeption;

}
