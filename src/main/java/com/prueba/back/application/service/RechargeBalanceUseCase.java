package com.prueba.back.application.service;

import org.springframework.stereotype.Component;
import com.prueba.back.application.port.in.RechargeBalanceIn;
import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.RechargeBalanceOut;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.exeption.BusinessExeption;

@Component
public class RechargeBalanceUseCase implements RechargeBalanceIn{
	
	private final RechargeBalanceOut rechargeBalanceOut;
	private final CardBankOut cardBankOut;
	
	public RechargeBalanceUseCase(RechargeBalanceOut rechargeBalanceOut, CardBankOut cardBankOut) {
		this.rechargeBalanceOut=rechargeBalanceOut;
		this.cardBankOut=cardBankOut;
	}

	@Override
	public Boolean rechargeBalance(BankCardDomain bankCardDomain) throws BusinessExeption{
		cardBankOut.findCardBank(bankCardDomain.getCardId());
		rechargeBalanceOut.rechargeBalance(bankCardDomain);
		return true;
	}

}
