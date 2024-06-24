package com.prueba.back.application.service;

import org.springframework.stereotype.Component;

import com.prueba.back.application.port.in.BuyTransactionIn;
import com.prueba.back.application.port.out.AnulationTransactionOut;
import com.prueba.back.application.port.out.BuyTransactionOut;
import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.StatusCardOut;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.exeption.BusinessExeption;
import com.prueba.back.util.EnviromentGlobal;

@Component
public class BuyTransactionUseCase implements BuyTransactionIn{
	
	private final BuyTransactionOut buyTransactionOut;
	private final CardBankOut cardBankOut;
	private final StatusCardOut statusCardOut;
	
	public BuyTransactionUseCase(BuyTransactionOut buyTransactionOut, CardBankOut cardBankOut,
			AnulationTransactionOut anulationTransactionOut, StatusCardOut statusCardOut) {
		this.buyTransactionOut=buyTransactionOut;
		this.cardBankOut=cardBankOut;
		this.statusCardOut=statusCardOut;
	}

	/**
	 * Metodo para realizar una compra
	 * 
	 * @param BankCardDomain Corresponde a la informacion de la transaccion
	 * @return String se devolvera el numero de la transaccion
	 */
	@Override
	public String buyTransaction(BankCardDomain bankCardDomain) throws BusinessExeption {
		
		BankCardDomain bankCard = cardBankOut.findCardBank(bankCardDomain.getCardId());
		
		statusCardOut.validateStatusCard(bankCardDomain.getCardId(), EnviromentGlobal.ACTIVE_STATUS);
		
		if(bankCard.getBalance() < bankCardDomain.getPrice())
			throw new BusinessExeption("Saldo de la tarjeta insuficiente.");
		
		cardBankOut.changeBalance(bankCardDomain.getCardId(), bankCard.getBalance() - bankCardDomain.getPrice());
		
		String transactionId = buyTransactionOut.buyTransaction(bankCardDomain, bankCard.getId());
		
		return transactionId;
	}

}
