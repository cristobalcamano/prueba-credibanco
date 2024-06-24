package com.prueba.back.application.service;

import org.springframework.stereotype.Component;

import com.prueba.back.adapter.persistence.repository.BankCardRepository;
import com.prueba.back.application.port.in.ActivateCardIn;
import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.StatusCardOut;
import com.prueba.back.exeption.BusinessExeption;

@Component
public class ActivateCardUseCase implements ActivateCardIn{
	
	private final CardBankOut cardBankOut;
	private final StatusCardOut statusCardOut;
	
	public ActivateCardUseCase(StatusCardOut statusCardOut, BankCardRepository bankCardRepository,
			CardBankOut cardBankOut){
		this.statusCardOut=statusCardOut;
		this.cardBankOut=cardBankOut;
	}

	/**
	 * Metodo para activar una tarjeta por su numero
	 * 
	 * @param Number Corresponde al numer de tarjeta
	 * @return Boolean se devolvera el resultado de la transaccion ejm true
	 */
	@Override
	public Boolean activateCard(String number) throws BusinessExeption{
		cardBankOut.findCardBank(number);
		statusCardOut.activateCard(number);
		return true;
	}

}
