package com.prueba.back.application.service;

import org.springframework.stereotype.Component;

import com.prueba.back.application.port.in.CheckTransactionIn;
import com.prueba.back.application.port.out.CheckTransactionOut;
import com.prueba.back.domain.TransactionBuyDomain;
import com.prueba.back.exeption.BusinessExeption;

@Component
public class CheckTransactionUseCase implements CheckTransactionIn{
	
	private final CheckTransactionOut checkTransactionOut;

	public CheckTransactionUseCase(CheckTransactionOut checkTransactionOut) {
		this.checkTransactionOut=checkTransactionOut;
	}
	
	/**
	 * Metodo para buscar una tarjeta por su numero
	 * 
	 * @param Number Corresponde al numer de tarjeta
	 * @return BankCardDomain se devolvera el numero de tarjeta
	 */
	@Override
	public TransactionBuyDomain checkTransaction(String transactionId) throws BusinessExeption{
		TransactionBuyDomain response = checkTransactionOut.checkTransaction(transactionId);
		return response;
	}

}
