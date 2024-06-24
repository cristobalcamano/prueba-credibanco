package com.prueba.back.application.service;

import org.springframework.stereotype.Component;

import com.prueba.back.application.port.in.AnulationTransactionIn;
import com.prueba.back.application.port.out.AnulationTransactionOut;
import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.CheckTransactionOut;
import com.prueba.back.application.port.out.TransactionOut;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.domain.TransactionBuyDomain;
import com.prueba.back.exeption.BusinessExeption;
import com.prueba.back.util.EnviromentGlobal;
import com.prueba.back.util.UtilBank;

@Component
public class AnulationTransactionsUseCase implements AnulationTransactionIn{
	
	private final CheckTransactionOut checkTransactionOut;
	private final AnulationTransactionOut anulationTransactionOut;
	private final CardBankOut cardBankOut;
	private final TransactionOut transactionOut;
	
	public AnulationTransactionsUseCase(CheckTransactionOut checkTransactionOut, AnulationTransactionOut anulationTransactionOut,
			TransactionOut transactionOut, CardBankOut cardBankOut) {
		this.checkTransactionOut=checkTransactionOut;
		this.anulationTransactionOut=anulationTransactionOut;
		this.cardBankOut=cardBankOut;
		this.transactionOut=transactionOut;
	}

	/**
	 * Metodo para la anulación de una tarjeta
	 * 
	 * @param TransactionBuyDomain Corresponde al numer de tarjeta y numero de transaccion
	 * @return Boolean El resultado de la operacion ejm true
	 */
	@Override
	public Boolean anulationTransaction(TransactionBuyDomain transactionBuyDomain) throws BusinessExeption {
		//validar que exista la tarjeta
		BankCardDomain card = cardBankOut.findCardBank(transactionBuyDomain.getCardId());
		//Validar si existe la transaccion
		TransactionBuyDomain transaction = checkTransactionOut.checkTransaction(transactionBuyDomain.getTransactionId());
		//validar el estado sea activo de transaccion
		transactionOut.validateStatusTransaction(transactionBuyDomain.getTransactionId(), EnviromentGlobal.ACTIVE_STATUS);
		//devolver el dinero a la tarjeta
		cardBankOut.changeBalance(transactionBuyDomain.getCardId(), transaction.getSalesValue() + card.getBalance());
		//validar que no pasen mas de 24 horas
		
		if (UtilBank.valitaeDaterohours24(transaction.getCreatedDate()))
			throw new BusinessExeption("La fecha no esta dentro del rango permitido");
		//cambiar el estado de la transaccion
		anulationTransactionOut.anulationTransaction(transactionBuyDomain);
		return true;
	}

}
