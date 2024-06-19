package com.prueba.back.adapter.persistence.adapter.card;

import org.springframework.stereotype.Component;

import com.prueba.back.adapter.persistence.entity.BankCardEntity;
import com.prueba.back.adapter.persistence.entity.BankCardMapper;
import com.prueba.back.adapter.persistence.entity.ProductoTypeEntity;
import com.prueba.back.adapter.persistence.entity.StatusEntity;
import com.prueba.back.adapter.persistence.repository.BankCardRepository;
import com.prueba.back.application.port.out.CardBankOut;
import com.prueba.back.application.port.out.RechargeBalanceOut;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.exeption.BusinessExeption;
import com.prueba.back.util.EnviromentGlobal;
import com.prueba.back.util.UtilBank;

@Component
public class PersistenceAdapterBankCard implements CardBankOut, RechargeBalanceOut {
	
	private final BankCardRepository bankCardRepository;
	
	public PersistenceAdapterBankCard(BankCardRepository bankCardRepository) {
		this.bankCardRepository = bankCardRepository;
	}

	/**
	 * Metodo genera una nueva tarjeta 
	 * 
	 * @param productType Corresponde al tipo de producto del cual 
	 * 		sera la tarjeta Debito o Credito
	 * @return Boolean Indicando el estado de la transaccion true en satisfactorio o false en 
	 * 		transaccion fallida
	 */
	@Override
	public String generatCardBank(Long productType) {
		BankCardEntity bankCardEntity = new BankCardEntity();
		
		String datePlusThree = UtilBank.addThreeYearsToDate();

		bankCardEntity.setBalance(0.0);
		bankCardEntity.setCardholderName("");
		bankCardEntity.setExpiration(datePlusThree);
		String numberProduct = UtilBank.concatLongByLong(productType, UtilBank.generateTenNumbersRandom());
		bankCardEntity.setNumber(numberProduct);
		bankCardEntity.setProductType(new ProductoTypeEntity(productType));
		bankCardEntity.setStatus(new StatusEntity(EnviromentGlobal.INACTIVE_STATUS));
		
		bankCardRepository.save(bankCardEntity);
		
		return numberProduct;
	}

	/**
	 * Metodo para buscar una tarjeta por su numero
	 * 
	 * @param Number Corresponde al numer de tarjeta
	 * @return BankCardDomain se devolvera el numero de tarjeta
	 */
	@Override
	public BankCardDomain findCardBank(String number) throws BusinessExeption{
		BankCardDomain bce = bankCardRepository.findBynumber(number)
				.map(BankCardMapper::entityToDomain)
				.orElseThrow(() -> new BusinessExeption("El tipo de producto " + number + " no encontrado"));

		return bce;
	}

	@Override
	public void rechargeBalance(BankCardDomain bankCardDomain) {
		BankCardEntity 
		bce = bankCardRepository.findBynumber(bankCardDomain.getCardId()).orElse(null);;
		
		bce.setBalance(bankCardDomain.getBalance());
		
		bankCardRepository.save(bce);
		
	}

	@Override
	public void changeBalance(String cardId, Double newBalance) throws BusinessExeption {
		BankCardEntity 
		bce = bankCardRepository.findBynumber(cardId).orElse(null);;
		
		bce.setBalance(newBalance);
		
		bankCardRepository.save(bce);
	}

}
