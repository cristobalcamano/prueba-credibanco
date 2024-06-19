package com.prueba.back.adapter.persistence.adapter.transaction;

import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

import com.prueba.back.adapter.persistence.entity.BankCardEntity;
import com.prueba.back.adapter.persistence.entity.StatusEntity;
import com.prueba.back.adapter.persistence.entity.TransactionEntity;
import com.prueba.back.adapter.persistence.entity.TransactionMapper;
import com.prueba.back.adapter.persistence.repository.TransactionRepository;
import com.prueba.back.application.port.out.AnulationTransactionOut;
import com.prueba.back.application.port.out.BuyTransactionOut;
import com.prueba.back.application.port.out.CheckTransactionOut;
import com.prueba.back.application.port.out.TransactionOut;
import com.prueba.back.domain.BankCardDomain;
import com.prueba.back.domain.TransactionBuyDomain;
import com.prueba.back.exeption.BusinessExeption;
import com.prueba.back.util.EnviromentGlobal;
import com.prueba.back.util.UtilBank;

@Component
public class PersistenceAdapterTransaction implements BuyTransactionOut, CheckTransactionOut, AnulationTransactionOut, TransactionOut{

	private final TransactionRepository transactionRepository;
	
	public PersistenceAdapterTransaction(TransactionRepository transactionRepository) {
		this.transactionRepository=transactionRepository;
	}

	@Override
	public TransactionBuyDomain checkTransaction(String transactionId) throws BusinessExeption{
		return transactionRepository.findByTransactionId(transactionId)
		.map(TransactionMapper::entityToDomain)
		.orElseThrow(() -> new BusinessExeption("El tipo de producto " + transactionId + " no encontrado"));
	}

	@Override
	public String buyTransaction(BankCardDomain bankCardDomain, Long idTransaction) {
		TransactionEntity entity = new TransactionEntity();
		entity.setCard(new BankCardEntity(idTransaction));
		entity.setSaleValue(bankCardDomain.getPrice());
		entity.setStatudId(new StatusEntity(EnviromentGlobal.ACTIVE_STATUS));
		String transactionId = UtilBank.generateTenNumbersRandom();
		entity.setTransactionId(transactionId);
		entity.setCreationDate(LocalDateTime.now());
		transactionRepository.save(entity);
		return transactionId;
	}

	@Override
	public void anulationTransaction(TransactionBuyDomain transactionBuyDomain) {
		
		TransactionEntity tr = transactionRepository.findByTransactionId(transactionBuyDomain.getTransactionId()).orElse(null);
		tr.setStatudId(new StatusEntity(EnviromentGlobal.CANCEL_STATUS));
		transactionRepository.save(tr);
	}

	@Override
	public TransactionBuyDomain validateStatusTransaction(String transactionId, Long status) throws BusinessExeption{
		return transactionRepository.validateStatusTransaction(transactionId, status)
				.map(TransactionMapper::entityToDomain)
				.orElseThrow(() -> new BusinessExeption("El tipo de producto " + transactionId + " no encontrado"));
	}

}
