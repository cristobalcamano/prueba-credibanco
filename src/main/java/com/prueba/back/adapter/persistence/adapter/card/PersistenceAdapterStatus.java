package com.prueba.back.adapter.persistence.adapter.card;

import org.springframework.stereotype.Component;

import com.prueba.back.adapter.persistence.entity.BankCardEntity;
import com.prueba.back.adapter.persistence.entity.StatusEntity;
import com.prueba.back.adapter.persistence.repository.BankCardRepository;
import com.prueba.back.application.port.out.StatusCardOut;
import com.prueba.back.exeption.BusinessExeption;
import com.prueba.back.util.EnviromentGlobal;

@Component
public class PersistenceAdapterStatus implements StatusCardOut{
	
	private final BankCardRepository bankCardRepository;

	public PersistenceAdapterStatus(BankCardRepository bankCardRepository){
		this.bankCardRepository = bankCardRepository;
	}
	
	@Override
	public void activateCard(String cardId){
		
		BankCardEntity 
		bce = bankCardRepository.findBynumber(cardId).orElse(null);;
		
		bce.setStatus(new StatusEntity(EnviromentGlobal.ACTIVE_STATUS));
		
		bankCardRepository.save(bce);
	}

	@Override
	public void blockCard(String cardId) {
		BankCardEntity 
		bce = bankCardRepository.findBynumber(cardId).orElse(null);
		
		bce.setStatus(new StatusEntity(EnviromentGlobal.BLOCK_STATUS));
		
		bankCardRepository.save(bce);
	}

	@Override
	public void validateStatusCard(String cardId, Long status) throws BusinessExeption{
		bankCardRepository.validateStatusCard(cardId, status)
		.orElseThrow(() -> new BusinessExeption("El estado de la tarjeta es incorrecto."));
	}

}
