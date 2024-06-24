package com.prueba.back.application.service;

import org.springframework.stereotype.Component;

import com.prueba.back.application.port.in.ConsultBalanceIn;
import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.exeption.BusinessExeption;

@Component
public class ConsultBalanceUseCase implements ConsultBalanceIn{
	
	private final CardBankOut cardBankOut;

	public ConsultBalanceUseCase(CardBankOut cardBankOut) {
		this.cardBankOut=cardBankOut;
	}
	
	/**
	 * Metodo para consultar el balance de una tarjeta por su numero
	 * 
	 * @param cardId Corresponde al numer de tarjeta
	 * @return Double se devolvera el resultado de la operacion
	 */
	@Override
	public Double consultBalance(String cardId) throws BusinessExeption {
		BankCardDomain bankCardDomain = cardBankOut.findCardBank(cardId);
		return bankCardDomain.getBalance();
	}

}
