package com.prueba.back.application.port.out;

import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.exeption.BusinessExeption;

public interface CardBankOut {

	public String generatCardBank(Long productType);

	public BankCardDomain findCardBank(String cardId) throws BusinessExeption;

	public void changeBalance(String cardId, Double newBalance) throws BusinessExeption;

}
