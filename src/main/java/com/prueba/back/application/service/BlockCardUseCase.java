package com.prueba.back.application.service;

import org.springframework.stereotype.Component;
import com.prueba.back.application.port.in.BlockCardIn;
import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.StatusCardOut;
import com.prueba.back.exeption.BusinessExeption;

@Component
public class BlockCardUseCase implements BlockCardIn{

	private final CardBankOut cardBankOut;
	private final StatusCardOut statusCardOut;
	
	public BlockCardUseCase(StatusCardOut statusCardOut, CardBankOut cardBankOut){
		this.statusCardOut= statusCardOut;
		this.cardBankOut = cardBankOut;
	}
	
	/**
	 * Metodo para bloquear una tarjeta por su numero
	 * 
	 * @param Number Corresponde al numer de tarjeta
	 * @return Boolean Resultado de la operacion ejem true
	 */
	@Override
	public Boolean BlockCard(String number) throws BusinessExeption{
		cardBankOut.findCardBank(number);
		statusCardOut.blockCard(number);
		return true;
	}

}
